package com.upc.pre.peaceapp.user.application.internal.queryservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.User;
import com.upc.pre.peaceapp.user.domain.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository users;

    public UserQueryServiceImpl(UserRepository users) {
        this.users = users;
    }

    @Override public Optional<User> getById(Long id) { return users.findById(id); }
    @Override public Optional<User> getByEmail(String usernameOrEmail) { return users.findByUsername(usernameOrEmail); }
    @Override public List<User> getAll() { return users.findAll(); }
}
