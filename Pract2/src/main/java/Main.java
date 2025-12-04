import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = Collections.emptyList();

        try {
            DataSource dataSource = new URLDataSource(filePath);
            TransactionParser parser = new CSVTransactionParser();
            transactions = parser.parse(dataSource.readLines());
        } catch (IOException e) {
            System.err.println("Помилка завантаження даних: " + e.getMessage());
        }

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        reportGenerator.printBalanceReport(analyzer.calculateTotalBalance());

        String monthYear = "01-2024";
        reportGenerator.printTransactionsCountByMonth(monthYear, analyzer.countTransactionsByMonth(monthYear));

        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        java.util.Map<String, Double> expensesByCategory = analyzer.calculateTotalExpensesByCategory();

        reportGenerator.printExpenseReport(expensesByCategory);
    }
}