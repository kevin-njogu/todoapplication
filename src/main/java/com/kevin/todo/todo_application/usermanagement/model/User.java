package com.kevin.todo.todo_application.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "_user", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(max = 30)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Size(max = 120)
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    private Roles role;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;

    private String twoFactorSecret;
    private boolean isTwoFactorEnabled = false;
    private String signUpMethod;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String email) {
        this.username = userName;
        this.email = email;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return userId != null && userId.equals(((User) o).getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getUserId() {
        return this.userId;
    }

    public @NotBlank @Size(max = 30) String getUsername() {
        return this.username;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return this.email;
    }

    public @Size(max = 120) String getPassword() {
        return this.password;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(@NotBlank @Size(max = 30) String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    @JsonIgnore
    public void setPassword(@Size(max = 120) String password) {
        this.password = password;
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

    public String toString() {
        return "User(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", createdDate=" + this.getCreatedDate() + ", updatedDate=" + this.getUpdatedDate() + ", accountNonLocked=" + this.isAccountNonLocked() + ", accountNonExpired=" + this.isAccountNonExpired() + ", credentialsNonExpired=" + this.isCredentialsNonExpired() + ", enabled=" + this.isEnabled() + ", credentialsExpiryDate=" + this.getCredentialsExpiryDate() + ", accountExpiryDate=" + this.getAccountExpiryDate() + ", twoFactorSecret=" + this.getTwoFactorSecret() + ", isTwoFactorEnabled=" + this.isTwoFactorEnabled() + ", signUpMethod=" + this.getSignUpMethod() + ")";
    }
}
