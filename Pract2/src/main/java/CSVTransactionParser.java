import java.util.ArrayList;
import java.util.List;

/**
 * Реалізація TransactionParser для обробки даних у форматі CSV.
 */
public class CSVTransactionParser implements TransactionParser {
    private static final String DELIMITER = ",";

    @Override
    public List<Transaction> parse(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(DELIMITER);
            if (values.length == 3) {
                try {
                    String date = values[0].trim();
                    double amount = Double.parseDouble(values[1].trim());
                    String description = values[2].trim();
                    transactions.add(new Transaction(date, amount, description));
                } catch (NumberFormatException e) {
                    System.err.println("Помилка парсингу числа у рядку, рядок пропущено: " + line);
                }
            } else {
                System.err.println("Некоректна кількість колонок, рядок пропущено: " + line);
            }
        }
        return transactions;
    }
}