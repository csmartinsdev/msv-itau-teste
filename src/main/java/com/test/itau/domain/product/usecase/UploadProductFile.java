package com.test.itau.domain.product.usecase;

import com.test.itau.exception.BusinessException;
import com.test.itau.exception.ServiceUnavailableException;
import com.test.itau.shared.response.ApiServiceResponse;
import com.test.itau.shared.service.S3Service;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadProductFile implements UseCase<MultipartFile, ApiServiceResponse> {
    private final S3Service s3Service;

    @Override
    public ApiServiceResponse execute(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()){
            throw new BusinessException("File is empty!!!");
        }

        try {
            s3Service.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        } catch (IOException e) {
            log.error("Error uploading file!!!",e);
            throw new ServiceUnavailableException(e.getMessage());
        }

        return ApiServiceResponse.builder()
                .message("File uploaded successfully!!!")
                .build();
    }
}
