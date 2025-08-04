package com.test.itau.shared.service;

import com.test.itau.exception.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class S3Service {
    private final S3Client s3Client;
    private final String bucket;

    public S3Service(S3Client s3Client, @Value(value = "${spring.cloud.aws.s3.bucket-name}") String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    public void uploadFile(String fileName, InputStream inputStream) {
      log.info("Uploading file {} to bucket {}", fileName, bucket);
      var request = PutObjectRequest.builder()
              .bucket(bucket)
              .key(fileName)
              .build();

        try {
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
        } catch (IOException e) {
            log.error("Error while uploading file {} to bucket {}", fileName, bucket, e);
            throw new ServiceUnavailableException(e.getMessage());
        }
    }
}
