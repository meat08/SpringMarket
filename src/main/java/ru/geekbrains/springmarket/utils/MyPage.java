package ru.geekbrains.springmarket.utils;

import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class MyPage<T> {

    private List<T> content;
    private Long totalElements;
    private Long totalPages;

    public MyPage(List<T> content, Pageable pageable, long total) {
        this.content = content;
        this.totalElements = total;
        this.totalPages = total / pageable.getPageSize();
    }

    public MyPage(List<T> content) {
        this.content = content;
        this.totalElements = (long) content.size();
        this.totalPages = 0L;
    }

    public MyPage() {
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
