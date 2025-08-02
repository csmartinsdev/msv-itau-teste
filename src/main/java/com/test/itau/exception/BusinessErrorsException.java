package com.test.itau.exception;

import com.test.itau.shared.response.ErrorDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class BusinessErrorsException extends RuntimeException {
    private transient List<ErrorDTO> errors;

    public BusinessErrorsException(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}
