package com.github.cazzruandev.springapirestbeginner.api.representationmodel.output;

import lombok.Data;

@Data
public class ClienteOutput {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
