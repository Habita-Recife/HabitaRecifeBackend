package br.com.habita_recife.habita_recife_backend.features_authentication.service;


import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    UserLoginDTO loginUser(UserLoginDTO userLoginDTO);
    List<User> listarTodos();
    Optional<User> findByEmail(UserDTO userDTO);
}
