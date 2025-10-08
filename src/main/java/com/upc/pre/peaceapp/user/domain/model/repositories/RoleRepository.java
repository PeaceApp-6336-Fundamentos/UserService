package com.upc.pre.peaceapp.user.domain.model.repositories;

import com.upc.pre.peaceapp.user.domain.model.entities.Role;
import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(Roles name);
    Role save(Role role);
}