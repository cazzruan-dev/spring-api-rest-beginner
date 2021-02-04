package com.github.cazzruandev.springapirestbeginner.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataPublicacao;

    @ManyToOne
    @Column(nullable = false)
    private OrdemServico ordemServico;
}
