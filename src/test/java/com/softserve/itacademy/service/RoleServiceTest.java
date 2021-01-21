package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
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
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateRole(){
        Role role = new Role();
        role.setName("Createrole");
        role = roleService.create(role);
        assertTrue(roleRepository.existsById(role.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadRole(){
        Role role = new Role();
        role.setName("Readrole");
        role = roleService.create(role);
        assertEquals(role, roleService.readById(role.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateRole(){
        Role role1 = new Role();
        Role role2;
        role1.setName("Updateerole");
        role1 = roleService.create(role1);
        role1.setName("Updateerole1");
        role2 = roleService.update(role1);
        assertEquals(role1, role2);
        assertEquals(role1.getName(), role2.getName());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteRole(){
        Role role = new Role();
        role.setName("Readrole");
        role = roleService.create(role);
        roleService.delete(role.getId());
        boolean assertion = false;
        try{
            roleService.readById(role.getId());
        }
        catch (EntityNotFoundException entityNotFoundException){
            assertion = true;
        }
        assertTrue(assertion);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllRoles(){
        List<Role> expectedRoles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("Readrole"+i);
            role = roleService.create(role);
            expectedRoles.add(role);
        }
        assertEquals(expectedRoles.size(), roleService.getAll().size());

    }

}
