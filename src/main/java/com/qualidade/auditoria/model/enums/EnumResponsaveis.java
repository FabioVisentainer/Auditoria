package com.qualidade.auditoria.model.enums;

public enum EnumResponsaveis {
    ENCTINT("Encarregado Tinturaria", "projetoauditora24@gmail.com"),
    ENCPCP("Encarregado PCP", "projetoauditora24@gmail.com"),
    GERPRO("Gerente de Produção", "projetoauditora24@gmail.com");

    private final String setor;
    private final String email;

    EnumResponsaveis(String setor, String email) {
        this.setor = setor;
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }

    public String getEmail() {
        return email;
    }
}