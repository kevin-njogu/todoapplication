package com.kevin.todo.todo_application.reconciler.dto;

public class ManualReconcileRequestDto {
    private int id;
    private boolean isReconciled;

    public ManualReconcileRequestDto(int id, boolean isReconciled) {
        this.id = id;
        this.isReconciled = isReconciled;
    }

    public ManualReconcileRequestDto() {
    }

    public static ManualReconcileRequestDtoBuilder builder() {
        return new ManualReconcileRequestDtoBuilder();
    }

    public int getId() {
        return this.id;
    }

    public boolean isReconciled() {
        return this.isReconciled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReconciled(boolean isReconciled) {
        this.isReconciled = isReconciled;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ManualReconcileRequestDto)) return false;
        final ManualReconcileRequestDto other = (ManualReconcileRequestDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.isReconciled() != other.isReconciled()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ManualReconcileRequestDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + (this.isReconciled() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "ManualReconcileRequestDto(id=" + this.getId() + ", isReconciled=" + this.isReconciled() + ")";
    }

    public static class ManualReconcileRequestDtoBuilder {
        private int id;
        private boolean isReconciled;

        ManualReconcileRequestDtoBuilder() {
        }

        public ManualReconcileRequestDtoBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ManualReconcileRequestDtoBuilder isReconciled(boolean isReconciled) {
            this.isReconciled = isReconciled;
            return this;
        }

        public ManualReconcileRequestDto build() {
            return new ManualReconcileRequestDto(this.id, this.isReconciled);
        }

        public String toString() {
            return "ManualReconcileRequestDto.ManualReconcileRequestDtoBuilder(id=" + this.id + ", isReconciled=" + this.isReconciled + ")";
        }
    }
}
