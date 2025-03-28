package br.com.habita_recife.habita_recife_backend.features_authentication.service.impl;

import br.com.habita_recife.habita_recife_backend.features_authentication.config.JwtTokenService;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.Role;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.RoleName;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.RoleRepository;
import br.com.habita_recife.habita_recife_backend.features_authentication.util.PasswordUtil;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordUtil passwordUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordUtil passwordUtil, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordUtil = passwordUtil;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public List<User> listarTodos() {
        return userRepository.findAll();
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
            try {
                RoleName roleEnum = RoleName.valueOf(roleName);

                Role role = roleRepository.findByRole(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleName));

                roles.add(role);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Role inválida: " + roleName);
            }
        }
        user.setRoles(roles);
        user.setVersion(0);

        User savedUser = userRepository.save(user);

        String token = jwtTokenService.generateToken(user.getEmail(), roles);

        Set<String> roleNames = new HashSet<>();
        for (Role role : savedUser.getRoles()) {
            roleNames.add(role.getRole().name());
        }

        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), token, roleNames);
    }

    @Override
    public UserLoginDTO loginUser(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        ));
        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        String token = jwtTokenService.generateToken(user.getEmail(), user.getRoles());

        UserLoginDTO responseDTO = new UserLoginDTO(user.getUsername(), user.getEmail(), token, user.getRoles());

        return responseDTO;
    }
}
