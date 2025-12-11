package ua.university.fsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestAutomatonTest {

    private final TestAutomatonFixed automaton = new TestAutomatonFixed();

    @ParameterizedTest(name = "Вхід: ''{0}'' -> Очікується: {1}")
    @CsvSource({
            "abcTESTabc, F",
            "abcTES, THREE",
            "TEST, F",
            "NO, S",
            "T, ONE",
            "TE, TWO",
            "TES, THREE",
            "TETEST, F",
            "TTTEST, F",
            "blaTETESTbla, F",
            "TESEST, ONE",
            "tEST, ONE",
            "T E S T, ONE",
            "TESTING, F"
    })
    void testAutomatonLogic(String input, State expectedState) {
        State result = automaton.process(input);
        Assertions.assertEquals(expectedState, result,
                "Помилка обробки рядка '" + input + "'");
    }
}