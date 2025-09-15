package com.qualidade.auditoria.model.enums;

public enum EnumConformidades {
    CONFORME(1, "Conforme"),
    NAO_CONFORME(2, "Não Conforme"),
    NAO_APLICA(3, "Não se Aplica");

    private final int codigo;
    private final String descricao;

    EnumConformidades(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}