package com.kevin.todo.todo_application.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;

    public LoginResponse(String jwtToken, String username, List<String> roles) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }

    public LoginResponse() {
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public String getUsername() {
        return this.username;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoginResponse)) return false;
        final LoginResponse other = (LoginResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$jwtToken = this.getJwtToken();
        final Object other$jwtToken = other.getJwtToken();
        if (this$jwtToken == null ? other$jwtToken != null : !this$jwtToken.equals(other$jwtToken)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $jwtToken = this.getJwtToken();
        result = result * PRIME + ($jwtToken == null ? 43 : $jwtToken.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }

    public String toString() {
        return "LoginResponse(jwtToken=" + this.getJwtToken() + ", username=" + this.getUsername() + ", roles=" + this.getRoles() + ")";
    }

    public static class LoginResponseBuilder {
        private String jwtToken;
        private String username;
        private List<String> roles;

        LoginResponseBuilder() {
        }

        public LoginResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public LoginResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginResponseBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(this.jwtToken, this.username, this.roles);
        }

        public String toString() {
            return "LoginResponse.LoginResponseBuilder(jwtToken=" + this.jwtToken + ", username=" + this.username + ", roles=" + this.roles + ")";
        }
    }
}
