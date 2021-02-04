package com.github.cazzruandev.springapirestbeginner.api.representationmodel.output;

import com.github.cazzruandev.springapirestbeginner.domain.enumeration.StatusOrdemServico;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class OrdemServicoOutput {

    private Long id;
    private ClienteResumo cliente;
    private String descricao;
    private BigDecimal preco;
    private StatusOrdemServico status;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;

}

