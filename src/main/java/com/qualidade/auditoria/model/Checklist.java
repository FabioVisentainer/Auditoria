package com.qualidade.auditoria.model;

import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_checklist")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeChecklist;
    private String nomeAuditor;

    @OneToMany(mappedBy = "idCheckList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respostas> respostas = new ArrayList<>();
}
