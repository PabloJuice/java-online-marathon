package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole1");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);
        assertTrue(userRepository.existsById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole1");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);
        assertEquals(user, userService.readById(user.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateUser(){
        Role role = new Role();
        role.setName("UserRole1");
        role = roleService.create(role);

        User user1 = new User();
        User user2;
        user1.setFirstName("Updateeuser");
        user1.setLastName("Userghgh");
        user1.setPassword("qwQW12!@");
        user1.setId(56);
        user1.setRole(role);
        user1.setEmail("valid2@gmail.ua");
        user1 = userService.create(user1);
        user1.setFirstName("Updateeuser1");
        user2 = userService.update(user1);
        assertEquals(user1, user2);
        assertEquals(user1.getFirstName(), user2.getFirstName());
    }

    @Test()
    @Transactional
    @DirtiesContext
    public void checkDeleteUser(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole1");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);
        userService.delete(user.getId());
        boolean assertion = false;
        try{
            userService.readById(user.getId());
        }
        catch (EntityNotFoundException entityNotFoundException){
            assertion = true;
        }
        assertTrue(assertion);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllUsers(){
        Role role = new Role();
        role.setName("UserRole1");
        role = roleService.create(role);

        List<User> expectedUsers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid"+i+"@gmail.ua");
            user.setFirstName("Create"+(char)(i+97));
            user.setLastName("User"+(char)(i+97));
            user.setPassword("qwQW12!@");
            user.setRole(role);
            user = userService.create(user);
            expectedUsers.add(user);
        }
        assertEquals(expectedUsers.size(), userService.getAll().size());

    }
}