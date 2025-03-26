package br.com.habita_recife.habita_recife_backend.features_authentication.service;


import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDTO userDTO);
    User loginUser(UserDTO userDTO);

    List<User> listarTodos();
}
