package com.test.itau.domain.media.api.v1;

import com.test.itau.domain.media.usecase.CreateMediaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/media")
public class MediaController {
    private final CreateMediaUseCase createMediaUseCase;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Init - Uploading file {}", file.getOriginalFilename());
        var response = createMediaUseCase.execute(file);
        response.whenComplete((url, exception) -> {
            if (exception != null) {
                log.error(exception.getMessage(), exception);
            }

            log.info("Finished Upload: {}", url);
        });

        return ResponseEntity.accepted().body("Upload started.");
    }
}
