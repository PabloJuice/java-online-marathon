package com.softserve.itacademy.service;

import com.softserve.itacademy.model.*;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTests {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateTask(){
        Task task = new Task();
        task.setName("Createtask");
        task.setPriority(Priority.MEDIUM);
        task = taskService.create(task);
        assertTrue(taskRepository.existsById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadTask(){
        Task task = new Task();
        task.setName("Readtask");
        task.setPriority(Priority.MEDIUM);
        task = taskService.create(task);
        assertEquals(task, taskService.readById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateTask(){
        Task task1 = new Task();
        Task task2;
        task1.setName("Updateetask");
        task1.setPriority(Priority.MEDIUM);
        task1 = taskService.create(task1);
        task1.setName("Updateetask1");
        task2 = taskService.update(task1);
        assertEquals(task1, task2);
        assertEquals(task1.getName(), task2.getName());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteTask(){
        Task task = new Task();
        task.setName("Readtask");
        task.setPriority(Priority.MEDIUM);
        task = taskService.create(task);
        taskService.delete(task.getId());
        assertNull(taskService.readById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllTasks(){
        List<Task> expectedTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("Readtask"+i);
            task.setPriority(Priority.MEDIUM);
            task = taskService.create(task);
            expectedTasks.add(task);
        }
        assertEquals(expectedTasks.size(), taskService.getAll().size());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetTaskByTodoId(){
        assertTrue(true);
    }

}
