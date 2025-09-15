package com.qualidade.auditoria.model;

import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_checklist")
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // deixa como autoIncrement
    private Integer idChecklist;

    private String nomeChecklist;

    private String nomeAuditor;

}
