package com.github.cazzruandev.springapirestbeginner.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{desc.not.blank}")
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataPublicacao;

    @ManyToOne
    @NotNull(message = "{ordemservico.not.null}")
    private OrdemServico ordemServico;
}
