package com.test.itau.domain.media.entity;

import com.test.itau.domain.media.enums.FileType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FileType type;


    public static Media convert(String name, FileType type) {
        var media = new Media();
        media.setName(name);
        media.setType(type);
        return media;
    }
}
