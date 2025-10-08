package com.upc.pre.peaceapp.user.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "iam_users")
public class UserJpaEntity /* extends AuditableAbstractAggregateRoot<UserJpaEntity> */ {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String username;

    @Column(length = 120, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RoleJpaEntity> roles = new HashSet<>();

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<RoleJpaEntity> getRoles() { return roles; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(Set<RoleJpaEntity> roles) { this.roles = roles; }
}
