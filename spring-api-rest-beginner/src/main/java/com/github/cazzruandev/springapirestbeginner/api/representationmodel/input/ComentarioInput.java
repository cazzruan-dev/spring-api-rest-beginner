package com.github.cazzruandev.springapirestbeginner.api.representationmodel.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ComentarioInput {

    @NotNull
    private String descricao;
}
