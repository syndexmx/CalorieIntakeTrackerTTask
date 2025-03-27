package com.github.syndexmx.caloryintaketracker.repositories;

import com.github.syndexmx.caloryintaketracker.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
    List<User> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity-graph")
    Optional<User> findById(Long id);
}
