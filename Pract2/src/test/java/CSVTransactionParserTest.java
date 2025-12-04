import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class CSVTransactionParserTest {

    @Test
    void testParseValidDataWithSpaces() {
        CSVTransactionParser parser = new CSVTransactionParser();
        List<String> lines = Arrays.asList(
                "05-12-2023,-450,Сільпо",                // Ідеальний рядок
                " 10-12-2023 , 8000 ,  Зарплата  "      // Рядок із зайвими пробілами
        );

        List<Transaction> result = parser.parse(lines);

        Assertions.assertEquals(2, result.size(), "Має бути розпізнано 2 транзакції");

        Assertions.assertEquals(-450.0, result.get(0).getAmount());
        Assertions.assertEquals("Сільпо", result.get(0).getDescription());

        Assertions.assertEquals(8000.0, result.get(1).getAmount(), "Сума має бути розпізнана коректно попри пробіли");
        Assertions.assertEquals("Зарплата", result.get(1).getDescription(), "Опис має бути без зайвих пробілів");
    }

    @Test
    void testParseInvalidLines() {
        CSVTransactionParser parser = new CSVTransactionParser();
        List<String> lines = Arrays.asList(
                "05-12-2023,-450,Good",          // Хороший рядок
                "Це просто текст без ком",       // Поганий: values.length != 3
                "05-12-2023,тисяча,BadNumber",   // Поганий: NumberFormatException
                "12-12-2023,100,Good2"           // Хороший рядок
        );

        List<Transaction> result = parser.parse(lines);

        Assertions.assertEquals(2, result.size(), "Помилкові рядки мають ігноруватися");

        Assertions.assertEquals("Good", result.get(0).getDescription());
        Assertions.assertEquals("Good2", result.get(1).getDescription());
    }
}
// 123