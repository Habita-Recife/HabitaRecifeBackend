package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.ForgotPasswordService;
import br.com.habita_recife.habita_recife_backend.features_authentication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    private final UserService userService;
    private final ForgotPasswordService forgotPasswordService;

    public UserController(UserService userService, ForgotPasswordService forgotPasswordService) {
        this.userService = userService;
        this.forgotPasswordService = forgotPasswordService;
    }


    @PostMapping("/register")
    @Operation(summary = "Registrar um usuário", description = "Cadastra um novo usuário no sistema.")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de usuários cadastrados no sistema.")
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> users = userService.listarTodos();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Realiza o login do usuario.")
    public ResponseEntity<UserLoginDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginDTO responseDTO = userService.loginUser(userLoginDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Solicitar redefinição de senha", description = "Envia um e-mail com um link para redefinir a senha.")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        forgotPasswordService.forgotPassword(email);
        return ResponseEntity.ok("Se o e-mail estiver cadastrado, um link de redefinição foi enviado.");
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Redefinir senha", description = "Redefine a senha do usuário com base no token de redefinição.")
    public ResponseEntity<String> forgotPasswordReset(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("As senhas não coincidem.");
        }
        forgotPasswordService.forgotPasswordReset(token, newPassword);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

}
