package com.github.gerdanyjr.controle_mestre_backend.model.converter;

import com.github.gerdanyjr.controle_mestre_backend.model.enums.CargoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CargoEnumConverter implements AttributeConverter<CargoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CargoEnum cargoEnum) {
        return cargoEnum.getCodigo();
    }

    @Override
    public CargoEnum convertToEntityAttribute(Integer codigo) {
        return CargoEnum.fromCodigo(codigo);
    }
}
