package com.test.itau.domain.media.usecase;

import com.test.itau.domain.media.entity.Media;
import com.test.itau.domain.media.repository.MediaRepository;
import com.test.itau.shared.response.PageDTO;
import com.test.itau.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListMediaUseCase implements UseCase<Pageable, PageDTO> {
    private final MediaRepository repository;

    @Override
    public PageDTO execute(Pageable pageable) {
        Page<Media> mediaPage = repository.findAll(pageable);

        return new PageDTO(mediaPage);
    }
}
