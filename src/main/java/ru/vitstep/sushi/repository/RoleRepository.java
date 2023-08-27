package ru.vitstep.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstep.sushi.model.Role;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   Optional <Role> findRoleByName(String name);

}
