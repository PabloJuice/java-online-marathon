package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoTests {
    private static ToDo validTodo;

    @BeforeAll
    static void init(){
        validTodo = new ToDo();
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.setName("Task1");
        task1.setPriority(Priority.HIGH);
        task2.setName("Task2");
        task2.setPriority(Priority.LOW);
        task3.setName("Task3");
        task3.setPriority(Priority.MEDIUM);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        validTodo.setTasks(taskList);
        validTodo.setOwner(new User());
        validTodo.setCreatedAt(LocalDateTime.parse("2021-01-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        validTodo.setTitle("Valid Title");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNameTodo")
    void checkTodoWithInvalidName(String input, String errorValue, int violationCount) {
        ToDo toDo = new ToDo();
        toDo.setTitle(input);
        toDo.setOwner(validTodo.getOwner());
        toDo.setTasks(validTodo.getTasks());
        toDo.setCreatedAt(validTodo.getCreatedAt());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
        assertEquals(violationCount, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidNameTodo(){
        return Stream.of(
                Arguments.of(" ", " ", 1),
                Arguments.of("", "", 2)
        );
    }

    @Test
    public void checkSetters(){
        User user = new User();
        ToDo toDo = new ToDo();
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.setName("Task1");
        task1.setPriority(Priority.HIGH);
        task2.setName("Task2");
        task2.setPriority(Priority.LOW);
        task3.setName("Task3");
        task3.setPriority(Priority.MEDIUM);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        toDo.setTasks(taskList);
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.parse("2021-01-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        toDo.setTitle("Valid Title");
        assertEquals(toDo.getTitle(), "Valid Title");
        assertEquals(toDo.getOwner(), user);
        assertEquals(toDo.getCreatedAt(), LocalDateTime.parse("2021-01-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertEquals(toDo.getTasks(), taskList);
    }
}
