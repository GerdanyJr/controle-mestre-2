package com.github.gerdanyjr.controle_mestre_backend.model.enums;

import java.util.HashMap;

public enum SexoEnum {
    FEMININO(1),
    MASCULINO(2),
    NAO_ESPECIFICADO(3);

    private final int codigo;
    private static HashMap<Integer, SexoEnum> map = new HashMap<>();

    static {
        for (SexoEnum sexo : SexoEnum.values()) {
            map.put(sexo.getCodigo(), sexo);
        }
    }

    SexoEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static SexoEnum fromCodigo(int codigo) {
        return map.getOrDefault(codigo, NAO_ESPECIFICADO);
    }
}
