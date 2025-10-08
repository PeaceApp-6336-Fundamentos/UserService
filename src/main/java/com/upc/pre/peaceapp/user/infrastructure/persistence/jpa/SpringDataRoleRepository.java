package com.upc.pre.peaceapp.user.infrastructure.persistence.jpa;

import com.upc.pre.peaceapp.user.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataRoleRepository extends JpaRepository<RoleJpaEntity, Long> {
    Optional<RoleJpaEntity> findByName(Roles name);
}
