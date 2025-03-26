package br.com.habita_recife.habita_recife_backend.features_authentication.service;


import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;

public interface UserService {

    User registerUser(UserDTO userDTO);
    UserLoginDTO loginUser(UserLoginDTO userLoginDTO);
}
