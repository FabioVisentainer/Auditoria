package com.qualidade.auditoria.model;

import com.qualidade.auditoria.model.enums.EnumConformidades;
import com.qualidade.auditoria.model.enums.EnumResponsaveis;
import com.qualidade.auditoria.model.enums.EnumSeveridade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_respostas")
public class Respostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResposta;

    @ManyToOne
    @JoinColumn(name = "id_checklist")
    private Checklist idCheckList;

    @ManyToOne
    @JoinColumn(name = "id_frase")
    private Frases idFrase;

    @Enumerated(EnumType.STRING)
    private EnumConformidades isConforme;

    @Enumerated(EnumType.STRING)
    private EnumSeveridade classificacao;

    private LocalDate dataResposta;

    @Enumerated(EnumType.STRING)
    private EnumResponsaveis idResponsavelAud;

    private String resolucaoIndicadaca;

    private Boolean isResolvido;

    @PrePersist
    @PreUpdate
    public void calcularDataResposta() {
        if (this.classificacao != null) {
            this.dataResposta = this.classificacao.calcularDataResposta();
        }
    }
}
