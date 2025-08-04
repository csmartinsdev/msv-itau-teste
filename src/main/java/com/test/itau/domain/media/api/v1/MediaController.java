package com.test.itau.domain.media.api.v1;

import com.test.itau.domain.media.usecase.CreateMediaUseCase;
import com.test.itau.domain.media.usecase.ListMediaUseCase;
import com.test.itau.shared.response.PageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/media")
public class MediaController {
    private final CreateMediaUseCase createMediaUseCase;
    private final ListMediaUseCase listMediaUseCase;

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

    @GetMapping
    public ResponseEntity<PageDTO> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("Init - Listing files");
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        var response = listMediaUseCase.execute(pageable);
        log.info("Finished Listing files");
        return ResponseEntity.ok().body(response);
    }
}
