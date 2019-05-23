package grocery;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Order> orders;
    private String name;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public String getReceipt() {
        StringBuilder message = new StringBuilder("Customer: " + name + "\n-------------\n");
        double cost = 0;
        double points = 0;
        boolean isProductOfWeek = false;
        for (Order order : orders) {
            message.append(order.getOrderMessage());
            double amount = order.getOrderAmount();
            double product_points = order.getOrderPoints(amount);
            isProductOfWeek = order.isContainProductOfWeek() || isProductOfWeek;
            cost  += amount;
            points += product_points;
            message.append("    ").append(amount).append(" TL");
            message.append(", ").append(product_points).append(" points\n");
        }
        // Add 2 points if the customer bought product of the week
        points = isProductOfWeek ? points + 2 : points;
        message.append("\n" + "Total cost: ").append(cost).append("\n").append("Total points: ").append(points);
        return message.toString();
    }
}
