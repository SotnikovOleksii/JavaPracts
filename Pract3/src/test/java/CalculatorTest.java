import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assertions.assertEquals(5.0, calculator.add(2.0, 3.0), 0.0001, "Додавання працює неправильно");
    }

    @Test
    public void testSubtract() {
        Assertions.assertEquals(2.0, calculator.subtract(5.0, 3.0), 0.0001);
    }

    @Test
    public void testMultiply() {
        Assertions.assertEquals(6.0, calculator.multiply(2.0, 3.0), 0.0001);
    }

    @Test
    public void testDivide() {
        Assertions.assertEquals(2.5, calculator.divide(5.0, 2.0), 0.0001);
    }

    @Test
    public void testSqrt() throws InvalidInputException {
        Assertions.assertEquals(4.0, calculator.sqrt(16.0), 0.0001);
    }

    @Test
    public void testDivideByZero() {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10.0, 0.0);
        });

        Assertions.assertEquals("Помилка: Ділення на нуль неможливе.", exception.getMessage());
    }

    @Test
    public void testSqrtNegative() {
        Exception exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            calculator.sqrt(-25.0);
        });

        Assertions.assertEquals("Помилка: Неможливо знайти корінь з від'ємного числа.", exception.getMessage());
    }
}