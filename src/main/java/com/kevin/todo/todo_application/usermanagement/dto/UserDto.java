package com.kevin.todo.todo_application.usermanagement.dto;

import com.kevin.todo.todo_application.usermanagement.model.Roles;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;
    private Roles role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserDto(Long userId, String userName, String email, boolean accountNonLocked, boolean accountNonExpired, boolean credentialsNonExpired, boolean enabled, LocalDate credentialsExpiryDate, LocalDate accountExpiryDate, String twoFactorSecret, boolean isTwoFactorEnabled, String signUpMethod, Roles role, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.credentialsExpiryDate = credentialsExpiryDate;
        this.accountExpiryDate = accountExpiryDate;
        this.twoFactorSecret = twoFactorSecret;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
        this.signUpMethod = signUpMethod;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public UserDto() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public LocalDate getCredentialsExpiryDate() {
        return this.credentialsExpiryDate;
    }

    public LocalDate getAccountExpiryDate() {
        return this.accountExpiryDate;
    }

    public String getTwoFactorSecret() {
        return this.twoFactorSecret;
    }

    public boolean isTwoFactorEnabled() {
        return this.isTwoFactorEnabled;
    }

    public String getSignUpMethod() {
        return this.signUpMethod;
    }

    public Roles getRole() {
        return this.role;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCredentialsExpiryDate(LocalDate credentialsExpiryDate) {
        this.credentialsExpiryDate = credentialsExpiryDate;
    }

    public void setAccountExpiryDate(LocalDate accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    public void setTwoFactorSecret(String twoFactorSecret) {
        this.twoFactorSecret = twoFactorSecret;
    }

    public void setTwoFactorEnabled(boolean isTwoFactorEnabled) {
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public void setSignUpMethod(String signUpMethod) {
        this.signUpMethod = signUpMethod;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.isAccountNonLocked() != other.isAccountNonLocked()) return false;
        if (this.isAccountNonExpired() != other.isAccountNonExpired()) return false;
        if (this.isCredentialsNonExpired() != other.isCredentialsNonExpired()) return false;
        if (this.isEnabled() != other.isEnabled()) return false;
        final Object this$credentialsExpiryDate = this.getCredentialsExpiryDate();
        final Object other$credentialsExpiryDate = other.getCredentialsExpiryDate();
        if (this$credentialsExpiryDate == null ? other$credentialsExpiryDate != null : !this$credentialsExpiryDate.equals(other$credentialsExpiryDate))
            return false;
        final Object this$accountExpiryDate = this.getAccountExpiryDate();
        final Object other$accountExpiryDate = other.getAccountExpiryDate();
        if (this$accountExpiryDate == null ? other$accountExpiryDate != null : !this$accountExpiryDate.equals(other$accountExpiryDate))
            return false;
        final Object this$twoFactorSecret = this.getTwoFactorSecret();
        final Object other$twoFactorSecret = other.getTwoFactorSecret();
        if (this$twoFactorSecret == null ? other$twoFactorSecret != null : !this$twoFactorSecret.equals(other$twoFactorSecret))
            return false;
        if (this.isTwoFactorEnabled() != other.isTwoFactorEnabled()) return false;
        final Object this$signUpMethod = this.getSignUpMethod();
        final Object other$signUpMethod = other.getSignUpMethod();
        if (this$signUpMethod == null ? other$signUpMethod != null : !this$signUpMethod.equals(other$signUpMethod))
            return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        final Object this$updatedDate = this.getUpdatedDate();
        final Object other$updatedDate = other.getUpdatedDate();
        if (this$updatedDate == null ? other$updatedDate != null : !this$updatedDate.equals(other$updatedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + (this.isAccountNonLocked() ? 79 : 97);
        result = result * PRIME + (this.isAccountNonExpired() ? 79 : 97);
        result = result * PRIME + (this.isCredentialsNonExpired() ? 79 : 97);
        result = result * PRIME + (this.isEnabled() ? 79 : 97);
        final Object $credentialsExpiryDate = this.getCredentialsExpiryDate();
        result = result * PRIME + ($credentialsExpiryDate == null ? 43 : $credentialsExpiryDate.hashCode());
        final Object $accountExpiryDate = this.getAccountExpiryDate();
        result = result * PRIME + ($accountExpiryDate == null ? 43 : $accountExpiryDate.hashCode());
        final Object $twoFactorSecret = this.getTwoFactorSecret();
        result = result * PRIME + ($twoFactorSecret == null ? 43 : $twoFactorSecret.hashCode());
        result = result * PRIME + (this.isTwoFactorEnabled() ? 79 : 97);
        final Object $signUpMethod = this.getSignUpMethod();
        result = result * PRIME + ($signUpMethod == null ? 43 : $signUpMethod.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final Object $updatedDate = this.getUpdatedDate();
        result = result * PRIME + ($updatedDate == null ? 43 : $updatedDate.hashCode());
        return result;
    }

    public String toString() {
        return "UserDto(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", email=" + this.getEmail() + ", accountNonLocked=" + this.isAccountNonLocked() + ", accountNonExpired=" + this.isAccountNonExpired() + ", credentialsNonExpired=" + this.isCredentialsNonExpired() + ", enabled=" + this.isEnabled() + ", credentialsExpiryDate=" + this.getCredentialsExpiryDate() + ", accountExpiryDate=" + this.getAccountExpiryDate() + ", twoFactorSecret=" + this.getTwoFactorSecret() + ", isTwoFactorEnabled=" + this.isTwoFactorEnabled() + ", signUpMethod=" + this.getSignUpMethod() + ", role=" + this.getRole() + ", createdDate=" + this.getCreatedDate() + ", updatedDate=" + this.getUpdatedDate() + ")";
    }
}
