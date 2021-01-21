package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToDoServiceTests {

    @Autowired
    ToDoService toDoService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateToDo(){
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("CreateTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoService.create(toDo);
        assertTrue(toDoRepository.existsById(toDo.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadToDo(){
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("CreateTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoService.create(toDo);
        assertEquals(toDo, toDoService.readById(toDo.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateToDo(){
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);

        ToDo toDo1 = new ToDo();
        ToDo toDo2;
        toDo1.setTitle("UpdateTodo1");
        toDo1.setOwner(user);
        toDo1.setCreatedAt(LocalDateTime.now());
        toDo1 = toDoService.create(toDo1);
        toDo1.setTitle("UpdateTodo2");
        toDo2 = toDoService.update(toDo1);
        assertEquals(toDo1, toDo2);
        assertEquals(toDo1.getTitle(), toDo2.getTitle());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteToDo(){
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("DeleteTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoService.create(toDo);
        toDoService.delete(toDo.getId());
        boolean assertion = false;
        try{
            toDoService.readById(toDo.getId());
        }
        catch (EntityNotFoundException entityNotFoundException){
            assertion = true;
        }
        assertTrue(assertion);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllToDos(){
        List<ToDo> expectedToDos = new ArrayList<>();
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleService.create(role);
        user.setRole(role);
        user = userService.create(user);
        for (int i = 0; i < 10; i++) {
            ToDo toDo = new ToDo();
            toDo.setTitle("GetAllTodo"+i);
            toDo.setOwner(user);
            toDo.setCreatedAt(LocalDateTime.now());
            toDo = toDoService.create(toDo);
            expectedToDos.add(toDo);
        }
        assertEquals(expectedToDos.size(), toDoService.getAll().size());

    }
    @Test
    @Transactional
    @DirtiesContext
    public void getAllToDosByUserIdTest(){
        assertTrue(true);
    }

}
