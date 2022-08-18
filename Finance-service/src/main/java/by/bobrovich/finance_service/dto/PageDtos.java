package by.bobrovich.finance_service.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDtos<T> {
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean first;
    private final int numberOfElements;
    private final boolean last;
    private final List<T> content;

    public PageDtos(Page<T> page) {
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.first = page.isFirst();
        this.numberOfElements = page.getNumberOfElements();
        this.last = page.isLast();
        this.content = page.getContent();
    }

    public PageDtos(int page,
                    int size,
                    int totalPages,
                    long totalElements,
                    boolean first,
                    int numberOfElements,
                    boolean last,
                    List<T> content) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }

    public static <T> PageDtos<T> of(Page<T> page) {
        return new PageDtos<>(page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                page.getContent()
        );
    }

    public static <T, E> PageDtos<E> of(Page<T> page, List<E> content) {
        return new PageDtos<>(page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                content
        );
    }

}
