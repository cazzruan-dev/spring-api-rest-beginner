package com.github.cazzruandev.springapirestbeginner.domain.service;

import com.github.cazzruandev.springapirestbeginner.domain.model.Comentario;
import com.github.cazzruandev.springapirestbeginner.domain.model.OrdemServico;

import java.util.List;

public interface IGestaoOrdemServico {

    void verifyOrdemServicoExist(Long ordemServicoId);
    OrdemServico findOrdemServicoById(Long ordemServicoId);
    List<OrdemServico> findAllOrdemServico();
    OrdemServico saveOrdemServico(OrdemServico ordemServico);
    void deleteOrdemServicoById(Long ordemServicoId);
    void finishOrdemServicoById(Long ordemServicoId);
    void cancelOrdemServicoById(Long ordemServicoId);
    Comentario addComentario(Long ordemServicoId, String descricao);
    void deleteComentario(Long ordemServicoId,Long comentarioId);

}