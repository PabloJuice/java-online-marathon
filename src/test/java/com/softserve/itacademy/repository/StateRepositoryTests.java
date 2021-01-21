package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.State;
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
public class StateRepositoryTests {

    @Autowired
    StateRepository stateRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateState() {
        State state = new State();
        state.setName("StateCreate");
        state = stateRepository.save(state);
        long expectedId = state.getId();
        assertEquals(expectedId, state.getId());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteStateByState(){
        State state = new State();
        state.setName("StateDelete");
        state = stateRepository.save(state);
        stateRepository.delete(state);
        assertFalse(stateRepository.existsById(state.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteStateById(){
        State state = new State();
        state.setName("StateDeleteByID");
        state = stateRepository.save(state);
        long expectedId = state.getId();
        assertEquals(expectedId, state.getId());
        stateRepository.deleteById(state.getId());
        assertFalse(stateRepository.existsById(state.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteAllStates(){
        for (int i = 0; i < 10; i++) {
            State state = new State();
            state.setName("StateDelete" + i);
            stateRepository.save(state);
        }
        assertEquals(stateRepository.count(), 10);
        assertEquals(stateRepository.findAll().size(), 10);
        stateRepository.deleteAll();
        assertEquals(stateRepository.count(), 0);
        assertEquals(stateRepository.findAll().size(), 0);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkExistsState(){
        State state = new State();
        state.setName("StateExists");
        stateRepository.save(state);
        assertTrue(stateRepository.findAll().stream().anyMatch(state1 -> state1.getId() == state.getId()));
        assertTrue(stateRepository.existsById(state.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkFindState(){
        State state = new State();
        state.setName("StateFind");
        stateRepository.save(state);
        assertTrue(stateRepository.findAll().stream().anyMatch(state1 -> state1.getId() == state.getId()));
        assertEquals(state, stateRepository.findById(state.getId()).get());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReturnAll(){
        List<State> expectedStates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            State state = new State();
            state.setName("State" + i);
            stateRepository.save(state);
            expectedStates.add(state);
        }
        assertEquals(stateRepository.count(), 10);
        assertEquals(stateRepository.findAll().size(), 10);
        assertEquals(expectedStates, stateRepository.findAll());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkCounter(){
        Set<State> expectedStates = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            State state = new State();
            state.setName("State" + i);
            stateRepository.save(state);
            expectedStates.add(state);
        }
        assertEquals(stateRepository.count(), stateRepository.findAll().size(), expectedStates.size());
        assertEquals(stateRepository.count(), expectedStates.size());
        assertEquals(stateRepository.findAll().size(), expectedStates.size());
    }
}
