package com.qualidade.auditoria.model;

import com.qualidade.auditoria.model.enums.EnumConformidades;
import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import com.qualidade.auditoria.model.enums.EnumSeveridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_respostas")
public class Respostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_checklist", nullable = false)
    private Checklist checklist;

    @ManyToOne
    @JoinColumn(name = "id_frase", nullable = false)
    private Frases frase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnumConformidades isConforme;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnumSeveridade classificacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnumResponsaveis responsavelAud;

    @Column(length = 500)
    private String resolucaoIndicada;

    @Column(nullable = false)
    private boolean resolvido = false;
}
