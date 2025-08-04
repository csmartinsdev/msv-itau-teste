package com.test.itau.domain.media.usecase;

import com.test.itau.domain.media.entity.Media;
import com.test.itau.domain.media.enums.FileType;
import com.test.itau.domain.media.repository.MediaRepository;
import com.test.itau.domain.media.service.impl.FileStorageService;
import com.test.itau.exception.BusinessException;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateMediaUseCase implements UseCase<MultipartFile, CompletableFuture<String>> {
    private final MediaRepository repository;
    private final FileStorageService fileStorageService;

    @Override
    public CompletableFuture<String> execute(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new BusinessException("File is empty");
        }

        var fileType = FileType.fromContentType(file.getContentType());
        var media = Media.convert(file.getOriginalFilename(), fileType);
        repository.save(media);

        return fileStorageService.uploadFile(file);
    }
}
