import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        boolean isRunning = true;

        System.out.println("--- Калькулятор запущено ---");

        while (isRunning) {
            try {
                System.out.print("\nВведіть перше число: ");
                double num1 = scanner.nextDouble();

                System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
                String operation = scanner.next();

                double result = 0;
                boolean success = true;

                switch (operation) {
                    case "+":
                        System.out.print("Введіть друге число: ");
                        double num2_add = scanner.nextDouble();
                        result = calculator.add(num1, num2_add);
                        break;
                    case "-":
                        System.out.print("Введіть друге число: ");
                        double num2_sub = scanner.nextDouble();
                        result = calculator.subtract(num1, num2_sub);
                        break;
                    case "*":
                        System.out.print("Введіть друге число: ");
                        double num2_mul = scanner.nextDouble();
                        result = calculator.multiply(num1, num2_mul);
                        break;
                    case "/":
                        System.out.print("Введіть друге число: ");
                        double num2_div = scanner.nextDouble();
                        result = calculator.divide(num1, num2_div);
                        break;
                    case "sqrt":
                        result = calculator.sqrt(num1);
                        break;
                    default:
                        System.out.println("Помилка: Невідома операція.");
                        success = false;
                }

                if (success) {
                    System.out.printf("Результат: %.2f%n", result);
                }

            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введено некоректні дані (очікувалося число).");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Непередбачувана помилка: " + e.getMessage());
            }

            System.out.print("Бажаєте продовжити? (y/n): ");
            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("y")) {
                isRunning = false;
                System.out.println("--- Роботу завершено ---");
            }
        }

        scanner.close();
    }
}