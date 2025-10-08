package com.upc.pre.peaceapp.user.application.internal.commandservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.User;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;
import com.upc.pre.peaceapp.user.domain.model.entities.Role;
import com.upc.pre.peaceapp.user.domain.model.repositories.RoleRepository;
import com.upc.pre.peaceapp.user.domain.model.repositories.UserRepository;
import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository users;
    private final RoleRepository roles;

    public UserCommandServiceImpl(UserRepository users, RoleRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public Long handle(CreateUserCommand cmd) {
        // crea agregado y asegura rol por defecto ROLE_USER
        var user = User.create(cmd.username(), cmd.password());
        var defaultRole = roles.findByName(Roles.ROLE_USER)
                .orElseGet(() -> roles.save(new Role(Roles.ROLE_USER)));
        user.addRole(defaultRole);
        return users.save(user).id();
    }

    @Override
    public void handle(UpdateUserCommand cmd) {
        var user = users.findById(cmd.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.updateUsername(cmd.username());
        users.save(user);
    }

    @Override
    public void handle(DeleteUserCommand cmd) {
        users.deleteById(cmd.id());
    }
}
