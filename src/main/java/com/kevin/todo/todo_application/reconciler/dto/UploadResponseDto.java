package com.kevin.todo.todo_application.reconciler.dto;

public class UploadResponseDto {
    private String message;

    UploadResponseDto(String message) {
        this.message = message;
    }

    public static UploadResponseDtoBuilder builder() {
        return new UploadResponseDtoBuilder();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UploadResponseDto)) return false;
        final UploadResponseDto other = (UploadResponseDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UploadResponseDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "UploadResponseDto(message=" + this.getMessage() + ")";
    }

    public static class UploadResponseDtoBuilder {
        private String message;

        UploadResponseDtoBuilder() {
        }

        public UploadResponseDtoBuilder message(String message) {
            this.message = message;
            return this;
        }

        public UploadResponseDto build() {
            return new UploadResponseDto(this.message);
        }

        public String toString() {
            return "UploadResponseDto.UploadResponseDtoBuilder(message=" + this.message + ")";
        }
    }
}
