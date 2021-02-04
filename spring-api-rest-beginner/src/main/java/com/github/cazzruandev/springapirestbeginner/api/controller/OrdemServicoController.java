package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.input.OrdemServicoInput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.OrdemServicoOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.OrdemServico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    public ModelMapper modelMapper;

    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput){
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }

    private OrdemServicoOutput toModel(OrdemServico ordemServico){
        return modelMapper.map(ordemServico, OrdemServicoOutput.class);
    }

    private List<OrdemServicoOutput> toCollectionModel(List<OrdemServico> ordensServico){
        return ordensServico.stream()
                .map(ordemServico -> toModel(ordemServico))
                .collect(Collectors.toList());
    }

}
