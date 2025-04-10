package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.config.JwtTokenService;
import br.com.habita_recife.habita_recife_backend.domain.model.User;
import br.com.habita_recife.habita_recife_backend.domain.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.service.ForgotPasswordService;
import br.com.habita_recife.habita_recife_backend.util.PasswordUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final JwtTokenService jwtTokenService;
    private final EmailServiceImpl emailServiceImpl;

    public ForgotPasswordServiceImpl(UserRepository userRepository,
                                     JavaMailSender mailSender, JwtTokenService jwtTokenService, EmailServiceImpl emailServiceImpl) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.jwtTokenService = jwtTokenService;
        this.emailServiceImpl = emailServiceImpl;
    }

    public void forgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String resetToken = jwtTokenService.generateResetToken(email);

            String resetLink = "http://localhost:3001/RecuperarSenha?token=" + resetToken;

            String subject = "Redefinição de Senha";
            String body = "Olá, " + user.getUsername() + "!\n\n"
                    + "Para redefinir sua senha, clique no link abaixo:\n"
                    + resetLink + "\n\n"
                    + "Se você não solicitou essa alteração, ignore este e-mail.\n\n"
                    + "Equipe Habita Recife";

            emailServiceImpl.sendEmail(email, subject, body);
        } else {
            throw new RuntimeException("Email não cadastrado no sistema.");
        }

    }

    public void forgotPasswordReset(String token, String newPassword) {
        String email = jwtTokenService.getEmailFromToken(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(PasswordUtil.encodePassword(newPassword));
            userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}