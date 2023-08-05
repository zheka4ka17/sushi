package ru.vitstep.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitstep.sushi.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
