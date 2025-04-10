package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.UserDTO;
import br.com.habita_recife.habita_recife_backend.domain.dto.UserLoginDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.User;
import br.com.habita_recife.habita_recife_backend.service.ForgotPasswordService;
import br.com.habita_recife.habita_recife_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
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
    public ResponseEntity<UserLoginDTO> loginUser(
            @RequestBody UserLoginDTO userLoginDTO,
            HttpServletResponse response
    ) {
        UserLoginDTO responseDTO = userService.loginUser(userLoginDTO);

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", responseDTO.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

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

    @PostMapping("/refresh-token")
    @Operation(summary = "Renova o access token", description = "Gera um novo access token usando o refresh token do cookie.")
    public ResponseEntity<UserLoginDTO> refreshAccessToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        UserLoginDTO responseDTO = userService.refreshAccessToken(request);

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", responseDTO.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return ResponseEntity.ok(responseDTO);
    }

}
