package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PrefeituraRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.config.JwtTokenService;
import br.com.habita_recife.habita_recife_backend.domain.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Role;
import br.com.habita_recife.habita_recife_backend.domain.enums.RoleName;
import br.com.habita_recife.habita_recife_backend.domain.repository.RoleRepository;
import br.com.habita_recife.habita_recife_backend.service.EmailService;
import br.com.habita_recife.habita_recife_backend.util.PasswordUtil;
import br.com.habita_recife.habita_recife_backend.domain.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.User;
import br.com.habita_recife.habita_recife_backend.domain.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MoradorRepository moradorRepository;
    private final SindicoRepository sindicoRepository;
    private final PorteiroRepository porteiroRepository;
    private final PrefeituraRepository prefeituraRepository;
    private final RoleRepository roleRepository;
    private final PasswordUtil passwordUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           MoradorRepository moradorRepository,
                           SindicoRepository sindicoRepository,
                           PorteiroRepository porteiroRepository, PrefeituraRepository prefeituraRepository, RoleRepository roleRepository, PasswordUtil passwordUtil, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.moradorRepository = moradorRepository;
        this.sindicoRepository = sindicoRepository;
        this.porteiroRepository = porteiroRepository;
        this.prefeituraRepository = prefeituraRepository;
        this.roleRepository = roleRepository;
        this.passwordUtil = passwordUtil;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.emailService = emailService;
    }

    @Override
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail());
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia");
        }

        if (!passwordUtil.isValid(userDTO.getPassword())) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres e conter letras e números");
        }

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(PasswordUtil.encodePassword(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        Set<Role> roles = new HashSet<>();
        for (String roleName : userDTO.getRoles()) {
            RoleName roleEnum;
            try {
                roleEnum = RoleName.valueOf(roleName);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Role inválida: " + roleName);
            }

            boolean existeEntidade = false;
            switch (roleEnum) {
                case MORADOR:
                    existeEntidade = moradorRepository.findByEmailMorador(userDTO.getEmail()).isPresent();
                    break;
                case SINDICO:
                    existeEntidade = sindicoRepository.findByEmailSindico(userDTO.getEmail()).isPresent();
                    break;
                case PORTEIRO:
                    existeEntidade = porteiroRepository.findByEmailPorteiro(userDTO.getEmail()).isPresent();
                    break;
                case PREFEITURA:
                    existeEntidade = prefeituraRepository.findByEmailPrefeitura(userDTO.getEmail()).isPresent();
                    break;
                default:
                    throw new RuntimeException("Role não implementada: " + roleEnum);
            }
            if (!existeEntidade) {
                throw new RuntimeException("Email não está associado à entidade " + roleEnum);
            }

            Role role = roleRepository.findByRole(roleEnum)
                    .orElseGet(() -> {
                        Role novaRole = new Role();
                        novaRole.setRole(roleEnum);
                        return roleRepository.save(novaRole);
                    });
            roles.add(role);
        }

        user.setRoles(roles);
        user.setVersion(0);

        User savedUser = userRepository.save(user);

        String token = jwtTokenService.generateToken(user.getEmail(), user.getUsername(), roles);

        Set<String> roleNames = new HashSet<>();
        for (Role role : savedUser.getRoles()) {
            roleNames.add(role.getRole().name());
        }

        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), token, roleNames);
    }

    @Override
    public UserLoginDTO loginUser(UserLoginDTO userLoginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getEmail(),
                            userLoginDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email ou senha incorretos!");
        }

        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        String accessToken = jwtTokenService.generateToken(user.getEmail(), user.getUsername(), user.getRoles());
        String refreshToken = jwtTokenService.generateRefreshToken(user.getEmail(), user.getUsername(), user.getRoles());

        UserLoginDTO responseDTO = new UserLoginDTO(user.getUsername(), user.getEmail(), accessToken, refreshToken, user.getRoles());
        responseDTO.setRefreshToken(refreshToken);

        return responseDTO;
    }

    @Override
    public UserLoginDTO refreshAccessToken(HttpServletRequest request) {
        String refreshToken = getRefreshTokenFromCookie(request);

        if (refreshToken == null || !jwtTokenService.validateToken(refreshToken)) {
            throw new BadCredentialsException("Refresh token inválido ou ausente.");
        }

        String email = jwtTokenService.extractUsername(refreshToken);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException("Usuário não encontrado.");
        }

        User user = optionalUser.get();
        String newAccessToken = jwtTokenService.generateToken(user.getEmail(), user.getUsername(), user.getRoles());

        return new UserLoginDTO(user.getUsername(), user.getEmail(), refreshToken, newAccessToken, user.getRoles());
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
