package com.qualidade.auditoria.model.enums;

public enum EnumResponsaveis {
    ENCTINT("Encarregado Tinturaria", "lincoln.brunkow@pucpr.edu.br"),
    ENCPCP("Encarregado PCP", "lincoln.brunkow@pucpr.edu.br"),
    GERPRO("Gerente de Produção", "lincoln.brunkow@pucpr.edu.br");

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