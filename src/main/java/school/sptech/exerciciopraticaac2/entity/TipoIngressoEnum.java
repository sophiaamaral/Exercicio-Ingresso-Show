package school.sptech.exerciciopraticaac2.entity;

import lombok.Getter;

@Getter
public enum TipoIngressoEnum {
    INTEIRA("Inteira"),
    MEIA("Meia"),
    CORTESIA("Cortesia");

    private String descricao;

    TipoIngressoEnum(String descricao) {
        this.descricao = descricao;
    }
}
