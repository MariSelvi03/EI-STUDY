package BakeryDesign;

public class AdapterPatternExample {
    // Interface for the Bakery Machine
    public interface BakeryMachine {
        void bake();
    }

    // Old Oven class with an old baking method
    public static class OldOven {
        public void oldBakeMethod() {
            System.out.println("Baking with old oven");
        }
    }

    // Adapter class that adapts OldOven to the BakeryMachine interface
    public static class OvenAdapter implements BakeryMachine {
        private OldOven oldOven;

        public OvenAdapter(OldOven oldOven) {
            this.oldOven = oldOven;
        }

        @Override
        public void bake() {
            oldOven.oldBakeMethod();
        }
    }

    // Main class to test the Adapter Pattern
    public static void main(String[] args) {
        OldOven oldOven = new OldOven();
        BakeryMachine machine = new OvenAdapter(oldOven);
        machine.bake();
    }
}
