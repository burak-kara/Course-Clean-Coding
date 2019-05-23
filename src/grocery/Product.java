package grocery;

public class Product {
    public static final int SPECIAL = 0;
    public static final int DISCOUNT = 1;
    public static final int PRODUCT_OF_THE_WEEK = 2;
    public static final int LUXURY = 3;

    private int kind;
    private String name;
    private double price;

    public Product(int kind, String name, double price) {
        this.kind = kind;
        this.name = name;
        this.price = price;
    }

    public int getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public double getProductCost(double weight) {
        switch (kind) {
            case(SPECIAL):
                return weight <= 4 ? weight * price : 4 * price + (weight - 4) * price * 0.5;
            case(DISCOUNT):
                return weight > 5 ? 5 * price + (weight - 5) * price * 0.9 : weight * price;
            case(PRODUCT_OF_THE_WEEK):
                return weight * price * 0.6;
            default:
                return weight * price;
        }
    }

    public double getProductPoints(double amount) {
        return kind == PRODUCT_OF_THE_WEEK ? amount * 0.125 : amount * 0.05;
    }

    public boolean isProductOfWeek() {
        return kind == PRODUCT_OF_THE_WEEK;
    }

    public String getProductMessage(double weight) {
        return name + ": " + weight + " kg @ " + price + " TL/kg\n";
    }
}
