package com.kevin.todo.todo_application.reconciler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconcileRequestDto {
    @NotBlank
    private String account;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    ReconcileRequestDto(@NotBlank String account, LocalDate startDate, LocalDate endDate) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ReconcileRequestDtoBuilder builder() {
        return new ReconcileRequestDtoBuilder();
    }

    public @NotBlank String getAccount() {
        return this.account;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setAccount(@NotBlank String account) {
        this.account = account;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReconcileRequestDto)) return false;
        final ReconcileRequestDto other = (ReconcileRequestDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$account = this.getAccount();
        final Object other$account = other.getAccount();
        if (this$account == null ? other$account != null : !this$account.equals(other$account)) return false;
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate)) return false;
        final Object this$endDate = this.getEndDate();
        final Object other$endDate = other.getEndDate();
        if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReconcileRequestDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $account = this.getAccount();
        result = result * PRIME + ($account == null ? 43 : $account.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * PRIME + ($startDate == null ? 43 : $startDate.hashCode());
        final Object $endDate = this.getEndDate();
        result = result * PRIME + ($endDate == null ? 43 : $endDate.hashCode());
        return result;
    }

    public String toString() {
        return "ReconcileRequestDto(account=" + this.getAccount() + ", startDate=" + this.getStartDate() + ", endDate=" + this.getEndDate() + ")";
    }

    public static class ReconcileRequestDtoBuilder {
        private @NotBlank String account;
        private LocalDate startDate;
        private LocalDate endDate;

        ReconcileRequestDtoBuilder() {
        }

        public ReconcileRequestDtoBuilder account(@NotBlank String account) {
            this.account = account;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        public ReconcileRequestDtoBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        public ReconcileRequestDtoBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public ReconcileRequestDto build() {
            return new ReconcileRequestDto(this.account, this.startDate, this.endDate);
        }

        public String toString() {
            return "ReconcileRequestDto.ReconcileRequestDtoBuilder(account=" + this.account + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
        }
    }
}
