package com.test.itau.domain.media.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum FileType {
    IMAGE("image"),
    PDF("pdf"),
    VIDEO("video"),
    UNSUPPORTED("unsupported");
    private final String type;

    FileType(String value) {
        this.type = value;
    }

    public static FileType fromContentType(String contentType) {
        if (Objects.isNull(contentType)) {
            return UNSUPPORTED;
        }

        if (contentType.startsWith("image")) return IMAGE;
        if (contentType.startsWith("application/pdf")) return PDF;
        if (contentType.startsWith("video")) return VIDEO;
        return UNSUPPORTED;
    }
}
