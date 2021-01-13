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
    void checkUserWithInvalidPassword(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(input);
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidPasswordUser(){
        return Stream.of(
                Arguments.of("invalidpassword", "invalidpassword"),
                Arguments.of("12345678", "12345678"),
                Arguments.of("abc@$!%*?&", "abc@$!%*?&"),
                Arguments.of("213@$!%*?&", "213@$!%*?&"),
                Arguments.of("12345abcd", "12345abcd")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFirstNameUser")
    void checkUserWithInvalidFirstName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(input);
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidFirstNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("1234 abcd", "1234 abcd"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid")
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
    @MethodSource("provideInvalidToDos")
    void checkUserWithInvalidToDos(List<ToDo> input, List<ToDo> errorValue){
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(traineeRole);

        user.setTodos(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }
    private static Stream<Arguments> provideInvalidToDos(){
        Task invalidTask1 = new Task();
        Task invalidTask2 = new Task();
        Task invalidTask3 = new Task();
        invalidTask1.setName("Task1");
        invalidTask1.setPriority(Priority.HIGH);
        invalidTask2.setName("Task2");
        invalidTask2.setPriority(Priority.LOW);
        invalidTask3.setName("Task3");
        invalidTask3.setPriority(Priority.MEDIUM);

        return Stream.of(
                Arguments.of(
                        null, null)
        );
    }

}
