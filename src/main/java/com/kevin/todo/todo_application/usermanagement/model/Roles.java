package com.kevin.todo.todo_application.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private Role roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public Roles(Role roleName) {
        this.roleName = roleName;
    }
}
