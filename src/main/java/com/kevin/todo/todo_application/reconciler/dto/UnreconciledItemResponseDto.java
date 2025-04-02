package com.kevin.todo.todo_application.reconciler.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnreconciledItemResponseDto {

    private List<T> content;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean empty;
    private String message;

    public UnreconciledItemResponseDto(List<T> content, long totalElements, int totalPages, boolean last, int size, int number, int numberOfElements, boolean first, boolean empty, String message) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.empty = empty;
        this.message = message;
    }

    public UnreconciledItemResponseDto() {
    }

    public static UnreconciledItemResponseDtoBuilder builder() {
        return new UnreconciledItemResponseDtoBuilder();
    }

    public List<T> getContent() {
        return this.content;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public boolean isLast() {
        return this.last;
    }

    public int getSize() {
        return this.size;
    }

    public int getNumber() {
        return this.number;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public boolean isFirst() {
        return this.first;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public String getMessage() {
        return this.message;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UnreconciledItemResponseDto)) return false;
        final UnreconciledItemResponseDto other = (UnreconciledItemResponseDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        if (this.getTotalElements() != other.getTotalElements()) return false;
        if (this.getTotalPages() != other.getTotalPages()) return false;
        if (this.isLast() != other.isLast()) return false;
        if (this.getSize() != other.getSize()) return false;
        if (this.getNumber() != other.getNumber()) return false;
        if (this.getNumberOfElements() != other.getNumberOfElements()) return false;
        if (this.isFirst() != other.isFirst()) return false;
        if (this.isEmpty() != other.isEmpty()) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnreconciledItemResponseDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final long $totalElements = this.getTotalElements();
        result = result * PRIME + (int) ($totalElements >>> 32 ^ $totalElements);
        result = result * PRIME + this.getTotalPages();
        result = result * PRIME + (this.isLast() ? 79 : 97);
        result = result * PRIME + this.getSize();
        result = result * PRIME + this.getNumber();
        result = result * PRIME + this.getNumberOfElements();
        result = result * PRIME + (this.isFirst() ? 79 : 97);
        result = result * PRIME + (this.isEmpty() ? 79 : 97);
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "UnreconciledItemResponseDto(content=" + this.getContent() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", last=" + this.isLast() + ", size=" + this.getSize() + ", number=" + this.getNumber() + ", numberOfElements=" + this.getNumberOfElements() + ", first=" + this.isFirst() + ", empty=" + this.isEmpty() + ", message=" + this.getMessage() + ")";
    }

    public static class UnreconciledItemResponseDtoBuilder {
        private List<T> content;
        private long totalElements;
        private int totalPages;
        private boolean last;
        private int size;
        private int number;
        private int numberOfElements;
        private boolean first;
        private boolean empty;
        private String message;

        UnreconciledItemResponseDtoBuilder() {
        }

        public UnreconciledItemResponseDtoBuilder content(List<T> content) {
            this.content = content;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder last(boolean last) {
            this.last = last;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder size(int size) {
            this.size = size;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder number(int number) {
            this.number = number;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder numberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder first(boolean first) {
            this.first = first;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder empty(boolean empty) {
            this.empty = empty;
            return this;
        }

        public UnreconciledItemResponseDtoBuilder message(String message) {
            this.message = message;
            return this;
        }

        public UnreconciledItemResponseDto build() {
            return new UnreconciledItemResponseDto(this.content, this.totalElements, this.totalPages, this.last, this.size, this.number, this.numberOfElements, this.first, this.empty, this.message);
        }

        public String toString() {
            return "UnreconciledItemResponseDto.UnreconciledItemResponseDtoBuilder(content=" + this.content + ", totalElements=" + this.totalElements + ", totalPages=" + this.totalPages + ", last=" + this.last + ", size=" + this.size + ", number=" + this.number + ", numberOfElements=" + this.numberOfElements + ", first=" + this.first + ", empty=" + this.empty + ", message=" + this.message + ")";
        }
    }
}
