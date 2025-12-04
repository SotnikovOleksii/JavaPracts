import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderHistory {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "Історія замовлень порожня.";
        }

        StringBuilder sb = new StringBuilder("Історія замовлень:\n");
        int count = 1;
        for (Order order : orders) {
            sb.append("--- Замовлення #").append(count++).append(" ---\n");
            sb.append(order.toString()).append("\n");
        }
        return sb.toString();
    }
}