package com.upc.pre.peaceapp.user.infrastructure.persistence.jpa;

import com.upc.pre.peaceapp.user.domain.model.aggregates.User;
import com.upc.pre.peaceapp.user.domain.model.entities.Role;
import com.upc.pre.peaceapp.user.domain.model.repositories.RoleRepository;
import com.upc.pre.peaceapp.user.domain.model.repositories.UserRepository;
import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryJpaAdapter implements UserRepository, RoleRepository {

    private final SpringDataUserRepository users;
    private final SpringDataRoleRepository roles;

    public UserRepositoryJpaAdapter(SpringDataUserRepository users, SpringDataRoleRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    // ---------- mapping helpers ----------
    private static User toDomain(UserJpaEntity e) {
        var u = User.create(e.getUsername(), e.getPassword());
        u.setId(e.getId());
        var domainRoles = e.getRoles().stream().map(j -> {
            var r = new Role(j.getName()); r.setId(j.getId()); return r;
        }).collect(Collectors.toSet());
        u.addRoles(domainRoles.stream().toList());
        return u;
    }

    private static UserJpaEntity toEntity(User u) {
        var e = new UserJpaEntity();
        e.setId(u.id());
        e.setUsername(u.username());
        e.setPassword(u.password());
        var set = u.roles().stream()
                .map(r -> { var j = new RoleJpaEntity(r.getName()); j.setId(r.id()); return j; })
                .collect(Collectors.toSet());
        e.setRoles(set);
        return e;
    }

    // ---------- UserRepository ----------
    @Override public User save(User user) { return toDomain(users.save(toEntity(user))); }
    @Override public Optional<User> findById(Long id) { return users.findById(id).map(UserRepositoryJpaAdapter::toDomain); }
    @Override public Optional<User> findByUsername(String username) { return users.findByUsername(username).map(UserRepositoryJpaAdapter::toDomain); }
    @Override public List<User> findAll() { return users.findAll().stream().map(UserRepositoryJpaAdapter::toDomain).toList(); }
    @Override public void deleteById(Long id) { users.deleteById(id); }

    // ---------- RoleRepository ----------
    @Override public Optional<Role> findByName(Roles name) {
        return roles.findByName(name).map(j -> { var r = new Role(j.getName()); r.setId(j.getId()); return r; });
    }
    @Override public Role save(Role role) {
        var saved = roles.save(new RoleJpaEntity(role.getName()));
        role.setId(saved.getId());
        return role;
    }
}
