package com.upc.pre.peaceapp.user.application.internal.queryservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> getById(Long id);
    Optional<User> getByEmail(String usernameOrEmail);
    List<User> getAll();
}
