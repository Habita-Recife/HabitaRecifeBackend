package br.com.habita_recife.habita_recife_backend.features_authentication.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.features_authentication.config.JwtTokenService;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.util.PasswordUtil;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public UserServiceImpl(UserRepository userRepository, PasswordUtil passwordUtil, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Override
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
        user.setRoles(userDTO.getRoles());
        user.setVersion(0);

        User savedUser = userRepository.save(user);

        String token = jwtTokenService.generateToken(user.getEmail());


        return new UserDTO(savedUser.getUsername(), savedUser.getEmail(), token, savedUser.getRoles());

    }

    @Override
    public UserLoginDTO loginUser(UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        ));
        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        String token = jwtTokenService.generateToken(user.getEmail());

        UserLoginDTO responseDTO = new UserLoginDTO(user.getUsername(), user.getEmail(), token, user.getRoles());

        return responseDTO;
    }

}
