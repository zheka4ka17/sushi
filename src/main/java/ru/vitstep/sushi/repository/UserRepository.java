package ru.vitstep.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstep.sushi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
