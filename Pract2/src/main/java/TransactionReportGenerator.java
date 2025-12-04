import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printExpenseReport(Map<String, Double> expensesByCategory) {
        System.out.println("\n--- ЗВІТ ПО ВИТРАТАХ (текстова діаграма) ---");
        System.out.println("(* = 1000 грн)");

        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();

            double absAmount = Math.abs(amount);

            int starsCount = (int) (absAmount / 1000);

            String stars = "*".repeat(starsCount);


            if (starsCount == 0 && absAmount > 0) {
                stars = ".";
            }

            System.out.printf("%-20s | %s (%.0f грн)%n", category, stars, amount);
        }
    }
}