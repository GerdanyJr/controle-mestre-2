package com.github.gerdanyjr.controle_mestre_backend.model.converter;

import com.github.gerdanyjr.controle_mestre_backend.model.enums.SexoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoEnumConverter implements AttributeConverter<SexoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SexoEnum sexo) {
        return sexo != null ? sexo.getCodigo() : null;
    }

    @Override
    public SexoEnum convertToEntityAttribute(Integer codigo) {
        return SexoEnum.fromCodigo(codigo);
    }
}
