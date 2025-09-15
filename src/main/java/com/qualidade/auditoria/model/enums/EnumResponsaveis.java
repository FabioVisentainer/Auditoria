package com.qualidade.auditoria.model.enums;

public enum EnumResponsaveis {
    ENCTINT("Encarregado Tinturaria", "pzakrzevski@gmail.com"),
    ENCPCP("Encarregado PCP", "pzakrzevski@gmail.com"),
    GERPRO("Gerente de Produção", "pzakrzevski@gmail.com");

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