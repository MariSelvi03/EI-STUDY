package BakeryDesign;

public class FactoryPatternExample {

    // Interface for Bakery Products
    public interface BakeryProduct {
        void prepare();
    }

    // Abstract Factory class
    public static abstract class BakeryProductFactory {
        public abstract BakeryProduct createProduct();
    }

    // Concrete BakeryProduct: Cake
    public static class Cake implements BakeryProduct {
        @Override
        public void prepare() {
            System.out.println("Preparing Cake");
        }
    }

    // Concrete Factory for Cake
    public static class CakeFactory extends BakeryProductFactory {
        @Override
        public BakeryProduct createProduct() {
            return new Cake();
        }
    }

    // Concrete BakeryProduct: Cookie
    public static class Cookie implements BakeryProduct {
        @Override
        public void prepare() {
            System.out.println("Preparing Cookie");
        }
    }

    // Concrete Factory for Cookie
    public static class CookieFactory extends BakeryProductFactory {
        @Override
        public BakeryProduct createProduct() {
            return new Cookie();
        }
    }

    // Main class to test the Factory Pattern
    public static void main(String[] args) {
        BakeryProductFactory factory = new CakeFactory();
        BakeryProduct product = factory.createProduct();
        product.prepare();
    }
}
