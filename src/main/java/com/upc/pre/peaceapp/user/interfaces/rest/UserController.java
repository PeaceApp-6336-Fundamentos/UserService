package com.upc.pre.peaceapp.user.interfaces.rest;

import com.upc.pre.peaceapp.user.application.internal.commandservices.UserCommandService;
import com.upc.pre.peaceapp.user.application.internal.queryservices.UserQueryService;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCommandService commands;
    private final UserQueryService queries;

    public UserController(UserCommandService commands, UserQueryService queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserResource body) {
        var id = commands.handle(CreateUserCommandFromResourceAssembler.toCommand(body));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(queries.getById(id).map(UserResourceFromEntityAssembler::toResource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateUserResource body) {
        commands.handle(UpdateUserCommandFromResourceAssembler.toCommand(id, body));
        return ResponseEntity.ok(queries.getById(id).map(UserResourceFromEntityAssembler::toResource));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return queries.getByEmail(username)
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResource(u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"User not found\"}"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var list = queries.getAll().stream().map(UserResourceFromEntityAssembler::toResource).toList();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commands.handle(new DeleteUserCommand(id));
        return ResponseEntity.ok().build();
    }
}
