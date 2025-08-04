package com.test.itau.domain.media.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface UploadStrategy {
    CompletableFuture<String> upload(MultipartFile file);

    String getFileType();
}
