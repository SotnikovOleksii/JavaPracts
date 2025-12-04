import java.util.List;

/**
 * Інтерфейс для парсерів, які перетворюють список рядків
 * на список об'єктів Transaction.
 */
public interface TransactionParser {
    List<Transaction> parse(List<String> lines);
}