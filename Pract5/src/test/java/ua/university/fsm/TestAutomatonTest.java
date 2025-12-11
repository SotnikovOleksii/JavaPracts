package ua.university.fsm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestAutomatonTest {

    private final TestAutomatonFixed automaton = new TestAutomatonFixed();

    @ParameterizedTest(name = "Вхід: ''{0}'' -> Очікується: {1}")
    @CsvSource({
            // === Базові сценарії ===
            "abcTESTabc, F",
            "abcTES, THREE",
            "TEST, F",
            "NO, S",

            // === Перевірка станів ===
            "T, ONE",
            "TE, TWO",
            "TES, THREE",

            // === Сценарії "із зірочкою" (складні випадки) ===
            "TETEST, F",          // Фікс працює!
            "TTTEST, F",
            "blaTETESTbla, F",

            // === Виправлені очікування ===
            // Рядок закінчується на "T", тому стан має бути ONE, а не S
            "TESEST, ONE",
            "tEST, ONE",
            "T E S T, ONE",

            // === Інші випадки ===
            "TESTING, F"
    })
    void testAutomatonLogic(String input, State expectedState) {
        State result = automaton.process(input);
        Assertions.assertEquals(expectedState, result,
                "Помилка обробки рядка '" + input + "'");
    }
}