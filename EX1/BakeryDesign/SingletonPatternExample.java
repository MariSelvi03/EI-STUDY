package BakeryDesign;

public class SingletonPatternExample {

    // Singleton class for managing the bakery
    public static class BakeryManager {
        private static BakeryManager instance;

        private BakeryManager() {}

        public static BakeryManager getInstance() {
            if (instance == null) {
                instance = new BakeryManager();
            }
            return instance;
        }

        public void manageBakery() {
            System.out.println("Managing the bakery");
        }
    }

    // Main method to test the Singleton Pattern
    public static void main(String[] args) {
        BakeryManager manager1 = BakeryManager.getInstance();
        BakeryManager manager2 = BakeryManager.getInstance();

        // Test if both references point to the same instance
        if (manager1 == manager2) {
            System.out.println("Both references point to the same instance.");
        } else {
            System.out.println("Different instances were created.");
        }

        // Use the BakeryManager instance
        manager1.manageBakery();
    }
}
