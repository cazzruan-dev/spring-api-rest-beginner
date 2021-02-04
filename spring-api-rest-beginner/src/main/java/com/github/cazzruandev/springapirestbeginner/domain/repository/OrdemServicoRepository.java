package com.github.cazzruandev.springapirestbeginner.domain.repository;

import com.github.cazzruandev.springapirestbeginner.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
