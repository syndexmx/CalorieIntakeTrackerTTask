package com.github.syndexmx.caloryintaketracker.services;

import com.github.syndexmx.caloryintaketracker.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    User create(User user);
    List<User> listAll();
    Optional<User> findById(Long id);
    boolean isPresent(Long id);
    Optional<User> save(User user);
    void deleteById(Long id);
}
