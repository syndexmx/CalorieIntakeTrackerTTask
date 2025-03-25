package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.repositories.UserRepository;
import com.github.syndexmx.caloryintaketracker.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listAll() {
        final List<User> listOfUsers = userRepository.findAll();
        return listOfUsers;
    }

    @Override
    public Optional<User> findById(Long id) {
        final Optional<User> userFound = userRepository.findById(id);
        return userFound;
    }

    @Override
    public boolean isPresent(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public Optional<User> save(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (!exists) {
            return Optional.empty();
        }
        final User userSaved = userRepository.save(user);
        return Optional.ofNullable(userSaved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
