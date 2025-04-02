package com.kevin.todo.todo_application.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String description;
    private LocalDate targetDate;
    private boolean complete;
    private String ownerUsername;

    public Todo(Long id, String description, LocalDate targetDate, boolean complete, String ownerUsername) {
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
        this.complete = complete;
        this.ownerUsername = ownerUsername;
    }

    public Todo() {
    }

    public static TodoBuilder builder() {
        return new TodoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getTargetDate() {
        return this.targetDate;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public String getOwnerUsername() {
        return this.ownerUsername;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Todo)) return false;
        final Todo other = (Todo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$targetDate = this.getTargetDate();
        final Object other$targetDate = other.getTargetDate();
        if (this$targetDate == null ? other$targetDate != null : !this$targetDate.equals(other$targetDate))
            return false;
        if (this.isComplete() != other.isComplete()) return false;
        final Object this$ownerUsername = this.getOwnerUsername();
        final Object other$ownerUsername = other.getOwnerUsername();
        if (this$ownerUsername == null ? other$ownerUsername != null : !this$ownerUsername.equals(other$ownerUsername))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Todo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $targetDate = this.getTargetDate();
        result = result * PRIME + ($targetDate == null ? 43 : $targetDate.hashCode());
        result = result * PRIME + (this.isComplete() ? 79 : 97);
        final Object $ownerUsername = this.getOwnerUsername();
        result = result * PRIME + ($ownerUsername == null ? 43 : $ownerUsername.hashCode());
        return result;
    }

    public String toString() {
        return "Todo(id=" + this.getId() + ", description=" + this.getDescription() + ", targetDate=" + this.getTargetDate() + ", complete=" + this.isComplete() + ", ownerUsername=" + this.getOwnerUsername() + ")";
    }

    public static class TodoBuilder {
        private Long id;
        private String description;
        private LocalDate targetDate;
        private boolean complete;
        private String ownerUsername;

        TodoBuilder() {
        }

        public TodoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TodoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TodoBuilder targetDate(LocalDate targetDate) {
            this.targetDate = targetDate;
            return this;
        }

        public TodoBuilder complete(boolean complete) {
            this.complete = complete;
            return this;
        }

        public TodoBuilder ownerUsername(String ownerUsername) {
            this.ownerUsername = ownerUsername;
            return this;
        }

        public Todo build() {
            return new Todo(this.id, this.description, this.targetDate, this.complete, this.ownerUsername);
        }

        public String toString() {
            return "Todo.TodoBuilder(id=" + this.id + ", description=" + this.description + ", targetDate=" + this.targetDate + ", complete=" + this.complete + ", ownerUsername=" + this.ownerUsername + ")";
        }
    }
}
