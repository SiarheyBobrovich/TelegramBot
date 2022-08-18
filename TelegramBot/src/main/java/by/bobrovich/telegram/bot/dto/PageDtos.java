package by.bobrovich.telegram.bot.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = PageDtos.Builder.class)
public class PageDtos<T> {
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean first;
    private final int numberOfElements;
    private final boolean last;
    private final List<T> content;

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

    public Builder<T> builder() {
        return new Builder<>();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder<T> {
        private int page;
        private int size;
        private int totalPages;
        private long totalElements;
        private boolean first;
        private int numberOfElements;
        private boolean last;
        private List<T> content;

        public void setPage(int page) {
            this.page = page;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public void setContent(List<T> content) {
            this.content = content;
        }

        public PageDtos<T> build() {
            return new PageDtos<>(page,
                    size,
                    totalPages,
                    totalElements,
                    first,
                    numberOfElements,
                    last,
                    content
            );
        }
    }

}
