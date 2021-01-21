package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(1, violations.size());
    }
    @Test
    public void checkSetters(){
        Role role = new Role();
        long roleId = role.getId();
        role.setName("Developer");
        List<User> users = new ArrayList<>();
        users.add(new User());
        role.setUsers(users);
        assertEquals(role.getName(), "Developer");
        assertEquals(role.getUsers(), users);
        assertEquals(role.getId(), roleId);
    }
    @Test
    void toStringTest(){
        Role role= new Role();
        role.setName("NEW_ROLE");
        String expected = "Role {name = NEW_ROLE}";
        assertEquals(expected, role.toString());
    }
}
