package com.github.syndexmx.caloryintaketracker.services.impl;

import com.github.syndexmx.caloryintaketracker.entities.TestUsers;
import com.github.syndexmx.caloryintaketracker.entities.User;
import com.github.syndexmx.caloryintaketracker.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    public void testThatUserIsCreated() {
        final User user = TestUsers.getTestUser();
        when(userRepository.save(eq(user))).thenReturn(user);
        final User savedUser = underTest.create(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void testThatListIsReturnedWhenExist() {
        final User user = TestUsers.getTestUser();
        final User user2 = TestUsers.getTestUser(2);
        List<User> listOfExisting = new ArrayList<>(List.of(user, user2));
        when(userRepository.findAll()).thenReturn(listOfExisting);
        final List<User> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
        for (User d : listOfExisting) {
            boolean isFound = false;
            for (User r : result) {
                if (d.equals(r)) {
                    isFound = true;
                }
            }
            assertTrue(isFound);
        }
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final User nonExistentUser = TestUsers.getTestUser();
        final Long nonExistentId = Long.MIN_VALUE;
        when(userRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<User> foundOptionalUser = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundOptionalUser);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final User user = TestUsers.getTestUser();
        final Long id = user.getId();
        when(userRepository.findById(eq(id))).thenReturn(Optional.of(user));
        final Optional<User> foundGeneric = underTest.findById(id);
        assertEquals(Optional.of(user), foundGeneric);
    }

}