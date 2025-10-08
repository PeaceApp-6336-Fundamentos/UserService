package com.upc.pre.peaceapp.user.infrastructure.persistence.jpa;

import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private Roles name;

    public RoleJpaEntity() {}
    public RoleJpaEntity(Roles name) { this.name = name; }

    public Long getId() { return id; }
    public Roles getName() { return name; }
    public void setId(Long id) { this.id = id; }
    public void setName(Roles name) { this.name = name; }
}
