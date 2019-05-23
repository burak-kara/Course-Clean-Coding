package grocery;

public class Order {
    private Product product;
    private double weight;

    public Order(Product product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getWeight() {
        return weight;
    }

    public double getOrderAmount() {
        return product.getProductCost(weight);
    }

    public double getOrderPoints(double amount) {
        return product.getProductPoints(amount);
    }

    public boolean isContainProductOfWeek() {
        return product.isProductOfWeek();
    }

    public String getOrderMessage() {
        return product.getProductMessage(weight);
    }
}
