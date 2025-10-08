package com.upc.pre.peaceapp.user.application.internal.commandservices;

import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    void handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
}
