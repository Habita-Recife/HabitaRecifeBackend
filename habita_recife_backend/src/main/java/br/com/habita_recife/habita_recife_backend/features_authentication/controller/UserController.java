package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginDTO responseDTO = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(responseDTO);
    }

}