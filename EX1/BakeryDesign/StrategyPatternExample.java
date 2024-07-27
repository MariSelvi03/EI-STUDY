package BakeryDesign;

public class StrategyPatternExample {

    // DiscountStrategy interface
    public interface DiscountStrategy {
        double applyDiscount(double price);
    }

    // NoDiscount class implementing DiscountStrategy
    public static class NoDiscount implements DiscountStrategy {
        @Override
        public double applyDiscount(double price) {
            return price;
        }
    }

    // Order class using DiscountStrategy
    public static class Order {
        private double price;
        private DiscountStrategy discountStrategy;

        public Order(double price) {
            this.price = price;
        }

        public void setDiscountStrategy(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        public double calculateFinalPrice() {
            return discountStrategy.applyDiscount(price);
        }
    }

    // Main class to test the Strategy Pattern
    public static void main(String[] args) {
        Order order = new Order(100);
        order.setDiscountStrategy(new NoDiscount());
        System.out.println("Final Price: " + order.calculateFinalPrice());
    }
}
