package com.kevin.todo.todo_application.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kevin.todo.todo_application.todo.entity.Todo;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoDto {
    private String description;
    private LocalDate targetDate;
    private boolean complete;
    private Todo todo;
    private List<Todo> todos;
    private String message;

    public TodoDto(String description, LocalDate targetDate, boolean complete, Todo todo, List<Todo> todos, String message) {
        this.description = description;
        this.targetDate = targetDate;
        this.complete = complete;
        this.todo = todo;
        this.todos = todos;
        this.message = message;
    }

    public TodoDto() {
    }

    public static TodoDtoBuilder builder() {
        return new TodoDtoBuilder();
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

    public Todo getTodo() {
        return this.todo;
    }

    public List<Todo> getTodos() {
        return this.todos;
    }

    public String getMessage() {
        return this.message;
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

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TodoDto)) return false;
        final TodoDto other = (TodoDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$targetDate = this.getTargetDate();
        final Object other$targetDate = other.getTargetDate();
        if (this$targetDate == null ? other$targetDate != null : !this$targetDate.equals(other$targetDate))
            return false;
        if (this.isComplete() != other.isComplete()) return false;
        final Object this$todo = this.getTodo();
        final Object other$todo = other.getTodo();
        if (this$todo == null ? other$todo != null : !this$todo.equals(other$todo)) return false;
        final Object this$todos = this.getTodos();
        final Object other$todos = other.getTodos();
        if (this$todos == null ? other$todos != null : !this$todos.equals(other$todos)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TodoDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $targetDate = this.getTargetDate();
        result = result * PRIME + ($targetDate == null ? 43 : $targetDate.hashCode());
        result = result * PRIME + (this.isComplete() ? 79 : 97);
        final Object $todo = this.getTodo();
        result = result * PRIME + ($todo == null ? 43 : $todo.hashCode());
        final Object $todos = this.getTodos();
        result = result * PRIME + ($todos == null ? 43 : $todos.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "TodoDto(description=" + this.getDescription() + ", targetDate=" + this.getTargetDate() + ", complete=" + this.isComplete() + ", todo=" + this.getTodo() + ", todos=" + this.getTodos() + ", message=" + this.getMessage() + ")";
    }

    public static class TodoDtoBuilder {
        private String description;
        private LocalDate targetDate;
        private boolean complete;
        private Todo todo;
        private List<Todo> todos;
        private String message;

        TodoDtoBuilder() {
        }

        public TodoDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TodoDtoBuilder targetDate(LocalDate targetDate) {
            this.targetDate = targetDate;
            return this;
        }

        public TodoDtoBuilder complete(boolean complete) {
            this.complete = complete;
            return this;
        }

        public TodoDtoBuilder todo(Todo todo) {
            this.todo = todo;
            return this;
        }

        public TodoDtoBuilder todos(List<Todo> todos) {
            this.todos = todos;
            return this;
        }

        public TodoDtoBuilder message(String message) {
            this.message = message;
            return this;
        }

        public TodoDto build() {
            return new TodoDto(this.description, this.targetDate, this.complete, this.todo, this.todos, this.message);
        }

        public String toString() {
            return "TodoDto.TodoDtoBuilder(description=" + this.description + ", targetDate=" + this.targetDate + ", complete=" + this.complete + ", todo=" + this.todo + ", todos=" + this.todos + ", message=" + this.message + ")";
        }
    }
}
