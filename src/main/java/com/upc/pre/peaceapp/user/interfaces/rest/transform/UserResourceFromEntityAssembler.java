package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.aggregates.User;
import com.upc.pre.peaceapp.user.domain.model.entities.Role;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResource(User u) {
        var roles = u.roles().stream().map(Role::getStringName).toList();
        return new UserResource(u.id(), u.username(), roles);
    }
}
