import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction t2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction t3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        double result = analyzer.calculateTotalBalance();

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід"); // Лютий
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата"); // Лютий
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід");  // Березень
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        // 2. Дія (Act)
        int countFeb = analyzer.countTransactionsByMonth("02-2023");
        int countMar = analyzer.countTransactionsByMonth("03-2023");

        // 3. Перевірка (Assert)
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий має бути 2");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень має бути 1");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -50.0, "Середня");
        Transaction t2 = new Transaction("01-01-2023", -100.0, "Велика");
        Transaction t3 = new Transaction("01-01-2023", -10.0, "Мала");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaction> result = analyzer.findTopExpenses();

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(-100.0, result.get(0).getAmount());
        Assertions.assertEquals(-50.0, result.get(1).getAmount());
    }
}