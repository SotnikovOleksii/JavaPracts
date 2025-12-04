import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Map;

public class TransactionAnalyzer {
    private List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        // Встановлюємо формат дати, який використовується у CSV файлі (день-місяць-рік)
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // НОВИЙ МЕТОД (Завдання 3)
    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);

            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Беремо тільки витрати (від'ємні числа)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортуємо (найменші числа, тобто найбільші витрати - нагорі)
                .limit(10) // Беремо лише топ-10
                .collect(Collectors.toList());
    }
    public Map<String, Double> calculateTotalExpensesByCategory() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Фільтруємо лише витрати
                .collect(Collectors.groupingBy(
                        Transaction::getDescription, // Групуємо за описом (категорією)
                        Collectors.summingDouble(Transaction::getAmount) // Сумуємо гроші в кожній групі
                ));
    }
}