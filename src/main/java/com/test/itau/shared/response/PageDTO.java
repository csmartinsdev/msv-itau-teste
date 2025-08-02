package com.test.itau.shared.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PageDTO {
    private static final Integer PAGE_SIZE = 1;
    private static final Integer TOTAL = 0;

    private final Page<?> page;

    public PageDTO() {
        this.page = new PageImpl<>(Collections.emptyList(), Pageable.ofSize(PAGE_SIZE), TOTAL);
    }

    public PageDTO(Page<?> page) {
        if (Objects.isNull(page)) {
            page = new PageImpl<>(Collections.emptyList(), Pageable.ofSize(PAGE_SIZE), TOTAL);
        }
        this.page = page;
    }

    public Integer getTotalPages() {
        return this.page.getTotalPages();
    }

    public Integer getPageSize() {
        return this.page.getSize();
    }

    public Long getTotalElements() {
        return this.page.getTotalElements();
    }

    public Boolean isSorted() {
        return this.page.getSort().isSorted();
    }

    public List<?> getContent() {
        return this.page.getContent();
    }

}
