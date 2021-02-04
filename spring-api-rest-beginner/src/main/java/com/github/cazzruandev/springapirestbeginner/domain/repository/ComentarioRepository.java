package com.github.cazzruandev.springapirestbeginner.domain.repository;

import com.github.cazzruandev.springapirestbeginner.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
