package com.softserve.itacademy.service;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class StateServiceTests {

    @Autowired
    StateService stateService;

    @Autowired
    StateRepository stateRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void checkCreateState(){
        State state = new State();
        state.setName("Createstate");
        state = stateService.create(state);
        assertTrue(stateRepository.existsById(state.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkReadState(){
        State state = new State();
        state.setName("Readstate");
        state = stateService.create(state);
        assertEquals(state, stateService.readById(state.getId()));
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkUpdateState(){
        State state1 = new State();
        State state2;
        state1.setName("Updateestate");
        state1 = stateService.create(state1);
        state1.setName("Updateestate1");
        state2 = stateService.update(state1);
        assertEquals(state1, state2);
        assertEquals(state1.getName(), state2.getName());
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkDeleteState(){
        State state = new State();
        state.setName("Readstate");
        state = stateService.create(state);
        stateService.delete(state.getId());
        boolean assertion = false;
        try{
            stateService.readById(state.getId());
        }
        catch (EntityNotFoundException entityNotFoundException){
            assertion = true;
        }
        assertTrue(assertion);
    }
    @Test
    @Transactional
    @DirtiesContext
    public void checkGetAllStates(){
        List<State> expectedStates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            State state = new State();
            state.setName("Readstate"+i);
            state = stateService.create(state);
            expectedStates.add(state);
        }
        assertEquals(expectedStates.size(), stateService.getAll().size());

    }

//    @Test
//    @Transactional
//    @DirtiesContext
//    public void checkAllStateSortedByNameTest(){
//        State s1 = new State();
//        s1.setName("Doing");
//        s1 = stateService.create(s1);
//        State s2 = new State();
//        s2.setName("Done");
//        s2 = stateService.create(s2);
//        State s3 = new State();
//        s3.setName("New");
//        s3 = stateService.create(s3);
//        State s4 = new State();
//        s4.setName("Verify");
//        s4 = stateService.create(s4);
//        List<State> expected = Arrays.asList(s1,s2,s3,s4);
//        List<State> actual = stateService.getSortAsc();
//        assertEquals(expected, actual);
//    }

}
