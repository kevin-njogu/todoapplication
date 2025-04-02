package com.kevin.todo.todo_application.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private Role roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    public Roles(Role roleName) {
        this.roleName = roleName;
    }

    public Roles(Integer roleId, Role roleName, Set<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public Roles() {
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public Role getRoleName() {
        return this.roleName;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(Role roleName) {
        this.roleName = roleName;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Roles)) return false;
        final Roles other = (Roles) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$roleId = this.getRoleId();
        final Object other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
        final Object this$roleName = this.getRoleName();
        final Object other$roleName = other.getRoleName();
        if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Roles;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $roleId = this.getRoleId();
        result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
        final Object $roleName = this.getRoleName();
        result = result * PRIME + ($roleName == null ? 43 : $roleName.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());
        return result;
    }

    public String toString() {
        return "Roles(roleId=" + this.getRoleId() + ")";
    }
}
