package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.ComentarioOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.Comentario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    public ModelMapper modelMapper;

    private ComentarioOutput toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioOutput.class);
    }

    private List<ComentarioOutput> toCollectionModel(List<Comentario> comentarios){
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
