import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {

    // Метод для виведення загального балансу
    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    // Метод для виведення кількості транзакцій за місяць
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
            double amount = entry.getValue(); // Тут буде від'ємне число, напр. -4500

            // 1. Перетворюємо на додатне число для розрахунків
            double absAmount = Math.abs(amount);

            // 2. Рахуємо кількість зірочок (сума / 1000)
            int starsCount = (int) (absAmount / 1000);

            // 3. Формуємо рядок із зірочок
            // (У Java 11+ можна використовувати "string".repeat(n))
            String stars = "*".repeat(starsCount);


            if (starsCount == 0 && absAmount > 0) {
                stars = "."; // (опціонально) щоб показати, що витрати були, але малі
            }

            System.out.printf("%-20s | %s (%.0f грн)%n", category, stars, amount);
        }
    }
}