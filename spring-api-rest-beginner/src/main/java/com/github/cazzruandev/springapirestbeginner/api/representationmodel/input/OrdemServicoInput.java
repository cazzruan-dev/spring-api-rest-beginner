package com.github.cazzruandev.springapirestbeginner.api.representationmodel.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrdemServicoInput {

    @NotBlank(message = "{desc.not.blank}")
    private String descricao;

    @NotNull(message = "{preco.not.null}")
    private BigDecimal preco;

    @Valid
    @NotNull(message = "{cliente.not.null}")
    private ClienteIdentityInput cliente;
}
