package com.github.cazzruandev.springapirestbeginner.api.representationmodel.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioOutput {

    private Long id;
    private String desc;
    private LocalDateTime dataPublicacao;

}
