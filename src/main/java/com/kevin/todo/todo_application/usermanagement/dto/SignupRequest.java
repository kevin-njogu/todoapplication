package com.kevin.todo.todo_application.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignupRequest() {
    }

    public @NotBlank @Size(min = 3, max = 30) String getUsername() {
        return this.username;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return this.email;
    }

    public @NotBlank @Size(min = 6, max = 40) String getPassword() {
        return this.password;
    }

    public void setUsername(@NotBlank @Size(min = 3, max = 30) String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank @Size(min = 6, max = 40) String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SignupRequest)) return false;
        final SignupRequest other = (SignupRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SignupRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "SignupRequest(username=" + this.getUsername() + ", email=" + this.getEmail() + ", role=" + this.getRole() + ", password=" + this.getPassword() + ")";
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
