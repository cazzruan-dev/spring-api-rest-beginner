package com.github.cazzruandev.springapirestbeginner.api.representationmodel.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ClienteInput {

    @NotBlank(message = "{nome.not.blank}")
    private String nome;

    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;

    @NotBlank(message = "{telefone.not.blank}")
    @Size(min = 11, max = 11, message = "{telefone.not.valid}")
    private String telefone;

}
