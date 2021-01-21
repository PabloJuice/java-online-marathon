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
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTest {


    @Test
    public void checkSetters(){
        State state = new State();
        state.setName("To Verify");
        long stateId = state.getId();
        assertEquals(state.getName(), "To Verify");
        assertEquals(stateId, state.getId());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStateName")
    void checkStateWithValidName(String input, String errorValue, int violationsSize) {
        State state = new State();
        state.setName(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(violationsSize, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidStateName(){
        return Stream.of(
                Arguments.of("invalid@name", "invalid@name", 1),
                Arguments.of("state[1]", "state[1]", 1),
                Arguments.of("", "", 3)
        );
    }
}
