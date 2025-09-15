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
@Table(name = "tb_frases")
public class Frases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // deixa como autoIncrement
    private Integer idFrase;

    private String frase;

    @Enumerated(EnumType.STRING) // salva o nome do enum no banco (ex: TI, RH)
    private EnumResponsaveis responsavel;

}

