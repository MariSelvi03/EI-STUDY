package BakeryDesign;

public class DecoratorPatternExample {

    // Interface for Bakery Items
    public interface BakeryItem {
        String getDescription();
        double cost();
    }

    // Concrete implementation of BakeryItem: Cakes
    public static class Cakes implements BakeryItem {
        @Override
        public String getDescription() {
            return "Cake";
        }

        @Override
        public double cost() {
            return 20.0;
        }
    }

    // Abstract decorator class for BakeryItem
    public static abstract class ToppingDecorator implements BakeryItem {
        protected BakeryItem bakeryItem;

        public ToppingDecorator(BakeryItem bakeryItem) {
            this.bakeryItem = bakeryItem;
        }
    }

    // Concrete decorator class for Chocolate Topping
    public static class ChocolateTopping extends ToppingDecorator {
        public ChocolateTopping(BakeryItem bakeryItem) {
            super(bakeryItem);
        }

        @Override
        public String getDescription() {
            return bakeryItem.getDescription() + ", Chocolate Topping";
        }

        @Override
        public double cost() {
            return bakeryItem.cost() + 5.0;
        }
    }

    // Main class to test the Decorator Pattern
    public static void main(String[] args) {
        BakeryItem cake = new Cakes();
        System.out.println(cake.getDescription() + " cost: " + cake.cost());

        BakeryItem chocolateCake = new ChocolateTopping(new Cakes());
        System.out.println(chocolateCake.getDescription() + " cost: " + chocolateCake.cost());
    }
}
