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
@Table(name = "tb_frases")
public class Frases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String frase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnumResponsaveis responsavel;

    @OneToMany(
            mappedBy = "frase",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Respostas> respostas = new ArrayList<>();
}
