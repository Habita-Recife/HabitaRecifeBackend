package br.com.habita_recife.habita_recife_backend.service;


import br.com.habita_recife.habita_recife_backend.domain.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.domain.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    UserLoginDTO loginUser(UserLoginDTO userLoginDTO);
    List<User> listarTodos();
    UserLoginDTO refreshAccessToken(HttpServletRequest request);
    Optional<User> findByEmail(UserDTO userDTO);
}
