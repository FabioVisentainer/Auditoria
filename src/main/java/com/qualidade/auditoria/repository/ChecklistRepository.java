package com.qualidade.auditoria.repository;

import com.qualidade.auditoria.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {

}
