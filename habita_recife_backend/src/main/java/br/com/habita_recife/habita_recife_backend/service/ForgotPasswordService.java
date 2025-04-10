package br.com.habita_recife.habita_recife_backend.service;

public interface ForgotPasswordService {
    void forgotPassword(String email);
    void forgotPasswordReset(String token, String email);
}
