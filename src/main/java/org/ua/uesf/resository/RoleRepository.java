package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
