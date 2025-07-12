package com.github.gerdanyjr.controle_mestre_backend.model.enums;

import java.util.HashMap;

public enum CargoEnum {
    GERENTE(1, "Gerente"),
    FUNCIONARIO(2, "Funcionário");

    private Integer codigo;
    private String descricao;
    private static HashMap<Integer, CargoEnum> map = new HashMap<>();

    CargoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    static {
        for (CargoEnum cargo : CargoEnum.values()) {
            map.put(cargo.getCodigo(), cargo);
        }
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CargoEnum fromCodigo(Integer codigo) {
        return map.get(codigo);
    }

}
