package com.github.cazzruandev.springapirestbeginner.domain.service;

import com.github.cazzruandev.springapirestbeginner.domain.enumeration.StatusOrdemServico;
import com.github.cazzruandev.springapirestbeginner.domain.exception.EntityNotFoundException;
import com.github.cazzruandev.springapirestbeginner.domain.model.Cliente;
import com.github.cazzruandev.springapirestbeginner.domain.model.Comentario;
import com.github.cazzruandev.springapirestbeginner.domain.model.OrdemServico;
import com.github.cazzruandev.springapirestbeginner.domain.repository.ClienteRepository;
import com.github.cazzruandev.springapirestbeginner.domain.repository.ComentarioRepository;
import com.github.cazzruandev.springapirestbeginner.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class GestaoOrdemServicoService implements IGestaoOrdemServico {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public void verifyOrdemServicoExist(Long ordemServicoId) {
        ordemServicoRepository.findById(ordemServicoId).orElseThrow(
                () -> new EntityNotFoundException("Entity ID: "+ ordemServicoId + " not found!"));
    }

    @Override
    public OrdemServico findOrdemServicoById(Long ordemServicoId) {
        verifyOrdemServicoExist(ordemServicoId);
        return ordemServicoRepository.findById(ordemServicoId).get();
    }

    @Override
    public List<OrdemServico> findAllOrdemServico() {
        return ordemServicoRepository.findAll();
    }

    @Override
    public OrdemServico saveOrdemServico(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente ID: " + ordemServico.getCliente().getId() + " not found!"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        OrdemServico ordemServicoSaved = ordemServicoRepository.save(ordemServico);
        return ordemServicoSaved;
    }

    @Override
    public void deleteOrdemServicoById(Long ordemServicoId) {
        verifyOrdemServicoExist(ordemServicoId);
        ordemServicoRepository.deleteById(ordemServicoId);
    }

    @Override
    public void finishOrdemServicoById(Long ordemServicoId) {
        OrdemServico ordemServico = findOrdemServicoById(ordemServicoId);
        ordemServico.finalizar();
        ordemServicoRepository.save(ordemServico);
    }

    @Override
    public void cancelOrdemServicoById(Long ordemServicoId) {
        OrdemServico ordemServico = findOrdemServicoById(ordemServicoId);
        ordemServico.cancelar();
        ordemServicoRepository.save(ordemServico);
    }

    @Override
    public Comentario addComentario(Long ordemServicoId, String descricao) {
        verifyOrdemServicoExist(ordemServicoId);
        OrdemServico ordemServico = findOrdemServicoById(ordemServicoId);
        Comentario comentario = new Comentario(null, descricao, LocalDateTime.now(), ordemServico);
        return comentarioRepository.save(comentario);
    }

    @Override
    public void deleteComentario(Long ordemServicoId, Long comentarioId) {
        verifyOrdemServicoExist(ordemServicoId);
        comentarioRepository.deleteById(comentarioId);
    }

}
