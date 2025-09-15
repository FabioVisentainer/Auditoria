package com.qualidade.auditoria.model.enums;

import java.time.LocalDate;

public enum EnumSeveridade {
    BAIXA(1, "Baixa", 1),
    MEDIA(2, "Média", 3),
    ALTA(3, "Alta", 5);

    private final int codigo;
    private final String descricao;
    private final int diasPrazo;

    EnumSeveridade(int codigo, String descricao, int diasPrazo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.diasPrazo = diasPrazo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDiasPrazo() {
        return diasPrazo;
    }

    public LocalDate calcularDataResposta() {
        return LocalDate.now().plusDays(diasPrazo);
    }
}