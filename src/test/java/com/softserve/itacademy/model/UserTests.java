package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

@SpringBootTest
public class UserTests {

    private static Role mentorRole;
    private static Role traineeRole;
    private static User validUser;

    @BeforeAll
    static void init(){
        mentorRole = new Role();
        mentorRole.setName("MENTOR");
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        validUser = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(traineeRole);
    }

    @Test
    void checkUserWithValidEmail() {
        User user = new User();
        user.setEmail("rty5@i.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(0, violations.size());
    }

    @Test
    void checkValidUserCreation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmailUser")
    void checkUserWithInvalidEmail(String input, String errorValue) {
        User user = new User();
        user.setEmail(input);
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidEmailUser(){
        return Stream.of(
                Arguments.of("invalidEmail", "invalidEmail"),
                Arguments.of("email@", "email@"),
                Arguments.of("", ""),
                Arguments.of("invalid", "invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPasswordUser")
    void checkUserWithInvalidPassword(String input, String errorValue, int violationCount) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(input);
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(violationCount, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidPasswordUser(){
        return Stream.of(
                Arguments.of("invalidpassword", "invalidpassword", 1),
                Arguments.of("12345678", "12345678", 1),
                Arguments.of("abc@$!%*?&", "abc@$!%*?&", 1),
                Arguments.of("213@$!%*?&", "213@$!%*?&", 1),
                Arguments.of("12345abcd", "12345abcd", 1),
                Arguments.of(" ", " ", 2),
                Arguments.of(null, null, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFirstNameUser")
    void checkUserWithInvalidFirstName(String input, String errorValue, int violationCount) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(input);
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(violationCount, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidFirstNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid", 1),
                Arguments.of("1234 abcd", "1234 abcd", 1),
                Arguments.of("Invalid-", "Invalid-", 1),
                Arguments.of("Invalid-invalid", "Invalid-invalid", 1),
                Arguments.of(" ", " ", 2),
                Arguments.of(null, null, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLastNameUser")
    void checkUserWithInvalidLastName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(input);
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidLastNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideToDos")
    void checkUserToDos(List<ToDo> input, List<ToDo> expectedResult){
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);
        user.setTodos(input);
        assertEquals(input, expectedResult);

    }
    private static Stream<Arguments> provideToDos(){
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
        ToDo toDo1 = new ToDo();
        toDo1.setTasks(taskList);

        List<ToDo> emptyToDoList = new ArrayList<>();
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(toDo1);

        return Stream.of(
                Arguments.of(
                        toDoList, toDoList),
                Arguments.of(
                        emptyToDoList, emptyToDoList),
                Arguments.of(
                        null, null)
        );
    }

}
