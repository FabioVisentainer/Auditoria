package com.qualidade.auditoria.repository;

import com.qualidade.auditoria.model.Checklist;
import com.qualidade.auditoria.model.Respostas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostasRepository extends JpaRepository<Respostas, Integer> {

}
