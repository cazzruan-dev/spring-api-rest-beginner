package com.github.cazzruandev.springapirestbeginner.domain.model;

import com.github.cazzruandev.springapirestbeginner.domain.enumeration.StatusOrdemServico;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ordens-servico")
public class OrdemServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @NotBlank
    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdemServico status;

    @NotBlank
    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataFinalizacao;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "ordemServico", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();
}
