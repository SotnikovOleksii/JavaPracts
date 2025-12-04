import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> catalog = new ArrayList<>();
        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        OrderHistory history = new OrderHistory();

        while (true) {
            System.out.println("\n--- МЕНЮ МАГАЗИНУ ---");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару (за назвою/категорією)");
            System.out.println("0 - Вийти");
            System.out.print("Виберіть опцію: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Некоректний ввід. Будь ласка, введіть число.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- КАТАЛОГ ТОВАРІВ ---");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int idAdd = scanner.nextInt();
                    Product productToAdd = catalog.stream()
                            .filter(p -> p.getId() == idAdd)
                            .findFirst()
                            .orElse(null);

                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар ID " + idAdd + " додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено");
                    }
                    break;

                case 3:
                    System.out.println("\n--- ВАШ КОШИК ---");
                    System.out.println(cart);
                    break;

                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        history.addOrder(order);
                        System.out.println("\n--- ЗАМОВЛЕННЯ ОФОРМЛЕНО ---");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Немає чого видаляти.");
                        break;
                    }
                    System.out.println("\n--- ТОВАРИ У КОШИКУ ---");
                    System.out.println(cart);
                    System.out.println("\nВведіть ID товару для видалення з кошика:");
                    int idRemove = scanner.nextInt();

                    Product productToRemove = cart.getProducts().stream()
                            .filter(p -> p.getId() == idRemove)
                            .findFirst()
                            .orElse(null);

                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Товар ID " + idRemove + " видалено з кошика.");
                    } else {
                        System.out.println("Товар з ID " + idRemove + " не знайдено у кошику.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- ІСТОРІЯ ЗАМОВЛЕНЬ ---");
                    System.out.println(history);
                    break;

                case 7:
                    scanner.nextLine();
                    System.out.println("Введіть пошуковий запит (назва або категорія):");
                    String query = scanner.nextLine().trim().toLowerCase();

                    List<Product> searchResults = new ArrayList<>();
                    for (Product product : catalog) {
                        String name = product.getName().toLowerCase();
                        String categoryName = product.getCategory().getName().toLowerCase();

                        if (name.contains(query) || categoryName.contains(query)) {
                            searchResults.add(product);
                        }
                    }

                    if (searchResults.isEmpty()) {
                        System.out.println("\nЗа запитом '" + query + "' товарів не знайдено.");
                    } else {
                        System.out.println("\n--- ЗНАЙДЕНІ ТОВАРИ ---");
                        for (Product product : searchResults) {
                            System.out.println(product);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}