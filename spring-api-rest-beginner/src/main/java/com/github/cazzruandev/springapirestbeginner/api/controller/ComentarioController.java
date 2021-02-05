package com.github.cazzruandev.springapirestbeginner.api.controller;

import com.github.cazzruandev.springapirestbeginner.api.representationmodel.input.ComentarioInput;
import com.github.cazzruandev.springapirestbeginner.api.representationmodel.output.ComentarioOutput;
import com.github.cazzruandev.springapirestbeginner.domain.model.Comentario;
import com.github.cazzruandev.springapirestbeginner.domain.repository.ComentarioRepository;
import com.github.cazzruandev.springapirestbeginner.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @GetMapping
    public List<ComentarioOutput> findAllComentarioByIdOrdemServico(@PathVariable Long ordemServicoId){
        return toCollectionModel(gestaoOrdemServicoService.findOrdemServicoById(ordemServicoId).getComentarios());
    }

    @PostMapping
    public ResponseEntity<ComentarioOutput> addComentario(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput){
        Comentario comentario = gestaoOrdemServicoService.addComentario(ordemServicoId, comentarioInput.getDescricao());
        ComentarioOutput comentarioOutput = toModel(comentario);
        return new ResponseEntity<>(comentarioOutput, HttpStatus.CREATED);
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long ordemServicoId, @PathVariable Long comentarioId){
        gestaoOrdemServicoService.deleteComentario(ordemServicoId, comentarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ComentarioOutput toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioOutput.class);
    }

    private List<ComentarioOutput> toCollectionModel(List<Comentario> comentarios){
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
