package com.test.itau.domain.media.service.impl;

import com.test.itau.domain.media.enums.FileType;
import com.test.itau.domain.media.service.UploadStrategy;
import com.test.itau.exception.ServiceUnavailableException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileStorageService {
    private final List<UploadStrategy> uploadStrategies;
    private Map<String, UploadStrategy> strategyMap;

    public FileStorageService(List<UploadStrategy> uploadStrategies) {
        this.uploadStrategies = uploadStrategies;
    }

    @PostConstruct
    public void init() {
        strategyMap = this.uploadStrategies.stream()
                .collect(Collectors.toMap(UploadStrategy::getFileType, Function.identity()));
    }

    @Async
    public CompletableFuture<String> uploadFile(MultipartFile file) {
        var fileType = FileType.fromContentType(file.getContentType());

        var strategy = strategyMap.get(fileType.getType());

        if (strategy == null) {
            return CompletableFuture.failedFuture(new ServiceUnavailableException("Unsupported file type " +
                    fileType.getType()));
        }

        return strategy.upload(file);
    }


}
