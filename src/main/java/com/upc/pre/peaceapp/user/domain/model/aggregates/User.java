package com.upc.pre.peaceapp.user.domain.model.aggregates;

import com.upc.pre.peaceapp.user.domain.model.entities.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private final Set<Role> roles = new HashSet<>();

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User create(String username, String password) {
        return new User(username, password);
    }

    // comportamiento (reglas)
    public User addRole(Role role) { this.roles.add(role); return this; }
    public User addRoles(List<Role> roles) { this.roles.addAll(roles); return this; }
    public void updateUsername(String username) { this.username = username; }

    // getters (estilo DDD, sin exponer setters generales)
    public Long id() { return id; }
    public String username() { return username; }
    public String password() { return password; }
    public Set<Role> roles() { return roles; }

    // setter de id sólo para mapear desde infraestructura
    public void setId(Long id) { this.id = id; }
}
