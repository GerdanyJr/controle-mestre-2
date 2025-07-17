package com.github.gerdanyjr.controle_mestre_backend.model.exceptions;

public class QuantidadeInsuficienteException extends RuntimeException {
    public QuantidadeInsuficienteException(String message) {
        super(message);
    }
}
