package br.com.habita_recife.habita_recife_backend.features_authentication.service.impl;

import br.com.habita_recife.habita_recife_backend.features_authentication.util.PasswordUtil;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public User registerUser(UserDTO userDTO) {

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

        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado!")
        );
    }
}
