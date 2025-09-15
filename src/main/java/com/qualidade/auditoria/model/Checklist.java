package com.qualidade.auditoria.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_checklist")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_checklist", nullable = false, length = 255)
    private String nomeChecklist;

    @Column(name = "nome_auditor", length = 255)
    private String nomeAuditor;

    @OneToMany(
            mappedBy = "checklist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Respostas> respostas = new ArrayList<>();

    // Construtor vazio
    public Checklist() {
    }

    // Construtor com todos os campos
    public Checklist(Long id, String nomeChecklist, String nomeAuditor, List<Respostas> respostas) {
        this.id = id;
        this.nomeChecklist = nomeChecklist;
        this.nomeAuditor = nomeAuditor;
        this.respostas = respostas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeChecklist() {
        return nomeChecklist;
    }

    public void setNomeChecklist(String nomeChecklist) {
        this.nomeChecklist = nomeChecklist;
    }

    public String getNomeAuditor() {
        return nomeAuditor;
    }

    public void setNomeAuditor(String nomeAuditor) {
        this.nomeAuditor = nomeAuditor;
    }

    public List<Respostas> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Respostas> respostas) {
        this.respostas = respostas;
    }
}
