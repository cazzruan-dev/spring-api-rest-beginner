package com.github.cazzruandev.springapirestbeginner.api.representationmodel.input;

import lombok.Data;

@Data
public class ClienteInput {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
