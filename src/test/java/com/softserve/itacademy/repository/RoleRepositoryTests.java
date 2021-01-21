package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateRole() {
        Role role = new Role();
        role.setName("NEW");
        role = roleRepository.save(role);
        long expectedId = role.getId();
        assertEquals(expectedId, role.getId());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteRoleByRole(){
        Role role = new Role();
        role.setName("RoleDelete");
        role = roleRepository.save(role);
        roleRepository.delete(role);
        assertFalse(roleRepository.existsById(role.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteRoleById(){
        Role role = new Role();
        role.setName("RoleDeleteByID");
        role = roleRepository.save(role);
        roleRepository.deleteById(role.getId());
        assertFalse(roleRepository.existsById(role.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteAllRoles(){
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("RoleDelete" + i);
            roleRepository.save(role);
        }
        assertEquals(roleRepository.count(), 10);
        assertEquals(roleRepository.findAll().size(), 10);
        roleRepository.deleteAll();
        assertEquals(roleRepository.count(), 0);
        assertEquals(roleRepository.findAll().size(), 0);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkExistsRole(){
        Role role = new Role();
        role.setName("RoleExists");
        roleRepository.save(role);
        assertTrue(roleRepository.findAll().stream().anyMatch(role1 -> role1.getId() == role.getId()));
        assertTrue(roleRepository.existsById(role.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkFindRole(){
        Role role = new Role();
        role.setName("RoleFind");
        roleRepository.save(role);
        assertTrue(roleRepository.findAll().stream().anyMatch(role1 -> role1.getId() == role.getId()));
        assertEquals(role, roleRepository.findById(role.getId()).get());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReturnAll(){
        List<Role> expectedRoles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("Role" + i);
            roleRepository.save(role);
            expectedRoles.add(role);
        }
        assertEquals(roleRepository.count(), 10);
        assertEquals(roleRepository.findAll().size(), 10);
        assertEquals(expectedRoles, roleRepository.findAll());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCounter(){
        Set<Role> expectedRoles = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("Role" + i);
            roleRepository.save(role);
            expectedRoles.add(role);
        }
        assertEquals(roleRepository.count(), roleRepository.findAll().size(), expectedRoles.size());
        assertEquals(roleRepository.count(), expectedRoles.size());
        assertEquals(roleRepository.findAll().size(), expectedRoles.size());
    }
}

