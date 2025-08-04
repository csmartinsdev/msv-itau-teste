package com.test.itau.shared.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private Integer code;
    private String field;
    private String message;
}
