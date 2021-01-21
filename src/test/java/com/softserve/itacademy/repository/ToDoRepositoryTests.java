package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToDoRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void createUserTest(){
        User user = new User();
        user.setEmail("valid1@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole1");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("Other");
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(user);
        toDo = toDoRepository.save(toDo);

        LocalDate localDate = toDo.getCreatedAt().toLocalDate();
        LocalDate today = LocalDate.now();
        assertEquals(localDate, today);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void exceptionWhenCreateNonUniqueToDoTest(){
        User user = new User();
        user.setEmail("valid2@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole2");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("ValidOther");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoRepository.save(toDo);

        LocalDate localDate = toDo.getCreatedAt().toLocalDate();
        LocalDate today = LocalDate.now();
        assertEquals(localDate, today);

        toDo = new ToDo();
        toDo.setTitle("ValidOther");
        toDo.setOwner(user);

        ToDo finalToDo = toDo;
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, ()->{
            toDoRepository.save(finalToDo);
        });
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateToDo() {
        User user = new User();
        user.setEmail("valid3@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole3");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("CreateTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoRepository.save(toDo);
        long expectedId = toDo.getId();
        assertEquals(expectedId, toDo.getId());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteToDoByToDo(){
        User user = new User();
        user.setEmail("valid4@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole4");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("DeleteTodo");
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(user);
        toDo = toDoRepository.save(toDo);
        toDoRepository.delete(toDo);
        assertFalse(toDoRepository.existsById(toDo.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteToDoById(){
        User user = new User();
        user.setEmail("valid5@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole5");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("DDeleteTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoRepository.save(toDo);
        toDoRepository.deleteById(toDo.getId());
        assertFalse(toDoRepository.existsById(toDo.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteAllToDos(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid6"+i+"@gmail.ua");
            user.setFirstName("Create");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole6"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            user = userRepository.save(user);

            ToDo toDo = new ToDo();
            toDo.setTitle("DeleteAllTodo" + i);
            toDo.setCreatedAt(LocalDateTime.now());
            toDo.setOwner(user);
            toDo = toDoRepository.save(toDo);
            toDoRepository.save(toDo);
        }
        assertEquals(toDoRepository.count(), 10);
        assertEquals(toDoRepository.findAll().size(), 10);
        toDoRepository.deleteAll();
        assertEquals(toDoRepository.count(), 0);
        assertEquals(toDoRepository.findAll().size(), 0);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkExistsToDo(){
        User user = new User();
        user.setEmail("valid7@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole7");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("CheckExistingTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoRepository.save(toDo);
        ToDo finalToDo = toDo;
        assertTrue(toDoRepository.findAll().stream().anyMatch(toDo1 -> toDo1.getId() == finalToDo.getId()));
        assertTrue(toDoRepository.existsById(toDo.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkFindToDo(){
        User user = new User();
        user.setEmail("valid8@gmail.ua");
        user.setFirstName("Create");
        user.setLastName("User");
        user.setPassword("qwQW12!@");
        Role role = new Role();
        role.setName("UserRole8");
        role = roleRepository.save(role);
        user.setRole(role);
        user = userRepository.save(user);

        ToDo toDo = new ToDo();
        toDo.setTitle("CheckFindingTodo");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo = toDoRepository.save(toDo);
        ToDo finalToDo = toDo;
        assertTrue(toDoRepository.findAll().stream().anyMatch(toDo1 -> toDo1.getId() == finalToDo.getId()));
        assertEquals(toDo, toDoRepository.findById(toDo.getId()).get());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReturnAll(){
        List<ToDo> expectedToDos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid9"+i+"@gmail.ua");
            user.setFirstName("Create");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole9"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            user = userRepository.save(user);

            ToDo toDo = new ToDo();
            toDo.setTitle("CheckReturnAll" + i);
            toDo.setOwner(user);
            toDo.setCreatedAt(LocalDateTime.now());
            toDo = toDoRepository.save(toDo);
            expectedToDos.add(toDo);
        }
        assertEquals(toDoRepository.count(), 10);
        assertEquals(toDoRepository.findAll().size(), 10);
        assertEquals(expectedToDos, toDoRepository.findAll());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCounter(){
        Set<ToDo> expectedToDos = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("valid100"+i+"@gmail.ua");
            user.setFirstName("Create");
            user.setLastName("User");
            user.setPassword("qwQW12!@");
            Role role = new Role();
            role.setName("UserRole10"+i);
            role = roleRepository.save(role);
            user.setRole(role);
            user = userRepository.save(user);

            ToDo toDo = new ToDo();
            toDo.setTitle("CheckCounter" + i);
            toDo.setOwner(user);
            toDo.setCreatedAt(LocalDateTime.now());
            toDo = toDoRepository.save(toDo);
            expectedToDos.add(toDo);
        }
        assertEquals(toDoRepository.count(), toDoRepository.findAll().size(), expectedToDos.size());
    }
}
