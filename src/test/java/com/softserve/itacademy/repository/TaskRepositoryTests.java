package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskRepositoryTests {
    @Autowired
    TaskRepository taskRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateTask() {
        Task task = new Task();
        task.setName("TaskCreate");
        task.setPriority(Priority.MEDIUM);
        task = taskRepository.save(task);
        long expectedId = task.getId();
        assertEquals(expectedId, task.getId());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteTaskByTask(){
        Task task = new Task();
        task.setName("TaskDelete");
        task.setPriority(Priority.MEDIUM);
        task = taskRepository.save(task);
        taskRepository.delete(task);
        assertFalse(taskRepository.existsById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteTaskById(){
        Task task = new Task();
        task.setName("TaskDeleteByID");
        task.setPriority(Priority.MEDIUM);
        task = taskRepository.save(task);
        long expectedId = task.getId();
        assertEquals(expectedId, task.getId());
        taskRepository.deleteById(task.getId());
        assertFalse(taskRepository.existsById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteAllTasks(){
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("TaskDelete" + i);
            task.setPriority(Priority.MEDIUM);
            taskRepository.save(task);
        }
        assertEquals(taskRepository.count(), 10);
        assertEquals(taskRepository.findAll().size(), 10);
        taskRepository.deleteAll();
        assertEquals(taskRepository.count(), 0);
        assertEquals(taskRepository.findAll().size(), 0);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkExistsTask(){
        Task task = new Task();
        task.setName("TaskExists");
        task.setPriority(Priority.MEDIUM);
        taskRepository.save(task);
        assertTrue(taskRepository.findAll().stream().anyMatch(task1 -> task1.getId() == task.getId()));
        assertTrue(taskRepository.existsById(task.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkFindTask(){
        Task task = new Task();
        task.setName("TaskFind");
        task.setPriority(Priority.MEDIUM);
        taskRepository.save(task);
        assertTrue(taskRepository.findAll().stream().anyMatch(task1 -> task1.getId() == task.getId()));
        assertEquals(task, taskRepository.findById(task.getId()).get());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReturnAll(){
        List<Task> expectedTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("Task" + i);
            task.setPriority(Priority.MEDIUM);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), 10);
        assertEquals(taskRepository.findAll().size(), 10);
        assertEquals(expectedTasks, taskRepository.findAll());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCounter(){
        Set<Task> expectedTasks = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("Task" + i);
            task.setPriority(Priority.MEDIUM);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), taskRepository.findAll().size(), expectedTasks.size());
        assertEquals(taskRepository.count(), expectedTasks.size());
        assertEquals(taskRepository.findAll().size(), expectedTasks.size());
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("Task" + i*10);
            task.setPriority(Priority.MEDIUM);
            taskRepository.save(task);
            expectedTasks.add(task);
        }
        assertEquals(taskRepository.count(), taskRepository.findAll().size(), expectedTasks.size());
        assertEquals(taskRepository.count(), expectedTasks.size());
        assertEquals(taskRepository.findAll().size(), expectedTasks.size());
    }
}
