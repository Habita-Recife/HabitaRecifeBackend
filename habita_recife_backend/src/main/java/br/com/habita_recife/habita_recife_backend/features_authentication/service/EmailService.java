package br.com.habita_recife.habita_recife_backend.features_authentication.service;

public interface EmailService {

    String sendEmail(String to, String subject, String body);
}
