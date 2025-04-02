package com.kevin.todo.todo_application.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kevin.todo.todo_application.usermanagement.model.User;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserResponse {
    private User user;
    private List<User> users;

    public GetUserResponse(User user, List<User> users) {
        this.user = user;
        this.users = users;
    }

    public GetUserResponse() {
    }

    public static GetUserResponseBuilder builder() {
        return new GetUserResponseBuilder();
    }

    public User getUser() {
        return this.user;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GetUserResponse)) return false;
        final GetUserResponse other = (GetUserResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GetUserResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        return result;
    }

    public String toString() {
        return "GetUserResponse(user=" + this.getUser() + ", users=" + this.getUsers() + ")";
    }

    public static class GetUserResponseBuilder {
        private User user;
        private List<User> users;

        GetUserResponseBuilder() {
        }

        public GetUserResponseBuilder user(User user) {
            this.user = user;
            return this;
        }

        public GetUserResponseBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public GetUserResponse build() {
            return new GetUserResponse(this.user, this.users);
        }

        public String toString() {
            return "GetUserResponse.GetUserResponseBuilder(user=" + this.user + ", users=" + this.users + ")";
        }
    }
}
