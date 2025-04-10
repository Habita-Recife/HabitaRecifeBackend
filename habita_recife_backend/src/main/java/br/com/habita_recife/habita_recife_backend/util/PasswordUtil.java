package br.com.habita_recife.habita_recife_backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private static final

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean isValid(String password) {
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Za-z].*") &&
                password.matches(".*[0-9].*");

    }

}