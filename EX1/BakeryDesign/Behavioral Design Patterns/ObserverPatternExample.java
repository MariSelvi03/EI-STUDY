package BakeryDesign;

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {

    // Observer interface
    public interface Observer {
        void update(String cakeName);
    }

    // Bakery class that maintains a list of observers and notifies them of updates
    public static class Bakery {
        private List<Observer> observers = new ArrayList<>();
        private String cakeName;

        public void setCake(String cakeName) {
            this.cakeName = cakeName;
            notifyObservers();
        }

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(cakeName);
            }
        }
    }

    // Customer class that implements the Observer interface
    public static class Customer implements Observer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        @Override
        public void update(String cakeName) {
            System.out.println(name + " has been notified of the new cake: " + cakeName);
        }
    }

    // Main class to test the Observer Pattern
    public static void main(String[] args) {
        Bakery bakery = new Bakery();
        bakery.addObserver(new Customer("Alice"));
        bakery.addObserver(new Customer("Bob"));

        bakery.setCake("Chocolate Cake");
    }
}
