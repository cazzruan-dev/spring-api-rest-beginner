package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.input.OrdemServicoInput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.ClienteOutput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.OrdemServicoOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;
import com.github.cazzruandev.springapirestbeginner.domain.model.OrdemServico;
import com.github.cazzruandev.springapirestbeginner.domain.service.IGestaoOrdemServico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public IGestaoOrdemServico gestaoOrdemServicoService;

    @GetMapping
    public List<OrdemServicoOutput> findAllCliente(){
        List<OrdemServico> ordensServico = gestaoOrdemServicoService.findAllOrdemServico();
        return toCollectionModel(ordensServico);
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoOutput> findOrdemServicoById(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico = gestaoOrdemServicoService.findOrdemServicoById(ordemServicoId);
        return new ResponseEntity<>(toModel(ordemServico), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoOutput> saveOrdemServico(@RequestBody @Valid OrdemServicoInput ordemServicoInput){
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        ordemServico = gestaoOrdemServicoService.saveOrdemServico(ordemServico);
        return new ResponseEntity<>(toModel(ordemServico), HttpStatus.CREATED);
    }

    @PutMapping("/{ordemServicoId}/finalizacao")
    public ResponseEntity<Void> finishOrdemServicoById(@PathVariable Long ordemServicoId){
        gestaoOrdemServicoService.finishOrdemServicoById(ordemServicoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{ordemServicoId}/cancelamento")
    public ResponseEntity<Void> cancelOrdemServicoById(@PathVariable Long ordemServicoId){
        gestaoOrdemServicoService.cancelOrdemServicoById(ordemServicoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{ordemServicoId}")
    public ResponseEntity<Void> deleteOrdemServicoById(@PathVariable Long ordemServicoId){
        gestaoOrdemServicoService.deleteOrdemServicoById(ordemServicoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
