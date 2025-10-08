package com.upc.pre.peaceapp.user.domain.model.entities;

import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;

public class Role {
    private Long id;
    private Roles name;

    public Role() {}
    public Role(Roles name) { this.name = name; }

    public Long id() { return id; }
    public Roles getName() { return name; }
    public String getStringName() { return name.name(); }

    public void setId(Long id) { this.id = id; }
}
