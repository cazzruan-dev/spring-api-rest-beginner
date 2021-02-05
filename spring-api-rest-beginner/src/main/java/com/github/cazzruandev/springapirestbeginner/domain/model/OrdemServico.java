package com.github.cazzruandev.springapirestbeginner.domain.model;

import com.github.cazzruandev.springapirestbeginner.domain.enumeration.StatusOrdemServico;
import com.github.cazzruandev.springapirestbeginner.domain.exception.NegocioException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ordens_servico")
public class OrdemServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdemServico status;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataFinalizacao;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "ordemServico", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    public void finalizar() {
        if(!StatusOrdemServico.ABERTA.equals(getStatus())){
            throw  new NegocioException("Ordem de serviço não pode ser finalizada");
        }
        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizacao(LocalDateTime.now());
    }

    public void cancelar() {
        if(!StatusOrdemServico.FINALIZADA.equals(getStatus())){
            throw  new NegocioException("Ordem de serviço não pode ser cancelada");
        }
        setStatus(StatusOrdemServico.CANCELADA);
        setDataFinalizacao(LocalDateTime.now());
    }

}
