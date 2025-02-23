package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notificacao;

}
