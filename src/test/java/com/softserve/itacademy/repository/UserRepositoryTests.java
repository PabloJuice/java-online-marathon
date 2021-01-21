package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateUser() {
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole1");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);
        long expectedId = user.getId();
        assertEquals(expectedId, user.getId());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteUserByUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Delete");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole2");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);
        userRepository.delete(user);
        assertFalse(userRepository.existsById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteUserById(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Dellete");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);
        long expectedId = user.getId();
        assertEquals(expectedId, user.getId());
        userRepository.deleteById(user.getId());
        assertFalse(userRepository.existsById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteAllUsers(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid2"+i+"@gmail.ua");
            user.setFirstName("Counter"+(char)(i+97));
            user.setLastName("User"+(char)(i+97));
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            userRepository.save(user);
        }
        assertEquals(userRepository.count(), 10);
        assertEquals(userRepository.findAll().size(), 10);
        userRepository.deleteAll();
        assertEquals(userRepository.count(), 0);
        assertEquals(userRepository.findAll().size(), 0);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkExistsUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Existed");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole5");
        role = roleRepository.save(role);
        user.setRole(role);
        userRepository.save(user);
        assertTrue(userRepository.findAll().stream().anyMatch(user1 -> user1.getId() == user.getId()));
        assertTrue(userRepository.existsById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkFindUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Found");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole6");
        role = roleRepository.save(role);
        user.setRole(role);
        userRepository.save(user);
        assertTrue(userRepository.findAll().stream().anyMatch(user1 -> user1.getId() == user.getId()));
        assertEquals(user, userRepository.findById(user.getId()).get());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReturnAll(){
        List<User> expectedUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid2"+i+"@gmail.ua");
            user.setFirstName("Counter"+(char)(i+97));
            user.setLastName("User"+(char)(i+97));
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), 10);
        assertEquals(userRepository.findAll().size(), 10);
        assertEquals(expectedUsers, userRepository.findAll());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCounter(){
        Set<User> expectedUsers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid2"+i+"@gmail.ua");
            user.setFirstName("Counter"+(char)(i+97));
            user.setLastName("User"+(char)(i+97));
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), userRepository.findAll().size(), expectedUsers.size());
        assertEquals(userRepository.count(), expectedUsers.size());
        assertEquals(userRepository.findAll().size(), expectedUsers.size());
        for (int i = 10; i < 20; i++) {
            User user = new User();
            user.setEmail("valid2"+i+"@gmail.ua");
            user.setFirstName("Counter"+(char)(i+97));
            user.setLastName("User"+(char)(i+97));
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            userRepository.save(user);
            expectedUsers.add(user);
        }
        assertEquals(userRepository.count(), userRepository.findAll().size(), expectedUsers.size());
        assertEquals(userRepository.count(), expectedUsers.size());
        assertEquals(userRepository.findAll().size(), expectedUsers.size());
    }
}
