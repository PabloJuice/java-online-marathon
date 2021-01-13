package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTests {
    @Test
    public void checkSetters(){
        Task task1 = new Task();
        long task1Id = task1.getId();
        task1.setName("Task1");
        task1.setPriority(Priority.HIGH);
        assertEquals(task1.getId(), task1Id);
        assertEquals(task1.getName(), "Task1");
        assertEquals(task1.getPriority(), Priority.HIGH);
    }
}