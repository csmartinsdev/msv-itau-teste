package com.test.itau.domain.media.service.impl;

import com.test.itau.domain.media.enums.FileType;
import com.test.itau.domain.media.service.UploadStrategy;
import com.test.itau.exception.ServiceUnavailableException;
import com.test.itau.shared.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class VideoUploadStrategy implements UploadStrategy {
    private static final String VIDEO = "videos/";

    private final S3Service s3Service;

    @Override
    public CompletableFuture<String> upload(MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Uploading video !!!");
            var filename = VIDEO + UUID.randomUUID() + file.getOriginalFilename();
            try {
                return s3Service.uploadFile(filename, file.getInputStream());
            } catch (IOException e) {
                log.error("Error uploading file!!!", e);
                throw new ServiceUnavailableException(e.getMessage());
            }
        });
    }

    @Override
    public String getFileType() {
        return FileType.VIDEO.getType();
    }
}
