package com.kevin.todo.todo_application.reconciler.dto;

public class ReconcileResponseDto {
    private String message;

    public ReconcileResponseDto(String message) {
        this.message = message;
    }

    public ReconcileResponseDto() {
    }

    public static ReconcileResponseDtoBuilder builder() {
        return new ReconcileResponseDtoBuilder();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReconcileResponseDto)) return false;
        final ReconcileResponseDto other = (ReconcileResponseDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReconcileResponseDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "ReconcileResponseDto(message=" + this.getMessage() + ")";
    }

    public static class ReconcileResponseDtoBuilder {
        private String message;

        ReconcileResponseDtoBuilder() {
        }

        public ReconcileResponseDtoBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ReconcileResponseDto build() {
            return new ReconcileResponseDto(this.message);
        }

        public String toString() {
            return "ReconcileResponseDto.ReconcileResponseDtoBuilder(message=" + this.message + ")";
        }
    }
}
