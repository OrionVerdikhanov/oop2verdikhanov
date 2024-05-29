import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Классы для горячих напитков
class HotDrink {
    private String name;
    private int volume;

    public HotDrink(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "HotDrink{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}

class HotDrinkWithTemperature extends HotDrink {
    private int temperature;

    public HotDrinkWithTemperature(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "HotDrinkWithTemperature{" +
                "name='" + getName() + '\'' +
                ", volume=" + getVolume() +
                ", temperature=" + temperature +
                '}';
    }
}

interface VendingMachine {
    HotDrinkWithTemperature getProduct(String name, int volume, int temperature);
}

class HotDrinksVendingMachine implements VendingMachine {
    private List<HotDrinkWithTemperature> products;

    public HotDrinksVendingMachine() {
        products = new ArrayList<>();
    }

    public void addProduct(HotDrinkWithTemperature product) {
        products.add(product);
    }

    @Override
    public HotDrinkWithTemperature getProduct(String name, int volume, int temperature) {
        for (HotDrinkWithTemperature product : products) {
            if (product.getName().equals(name) && product.getVolume() == volume && product.getTemperature() == temperature) {
                return product;
            }
        }
        return null;
    }
}

// Интерфейсы и классы для магазина
interface QueueBehaviour {
    void takeInQueue(Customer customer);
    void releaseFromQueue();
}

interface MarketBehaviour {
    void acceptOrder(Customer customer);
    void releaseOrder(Customer customer);
    void update();
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Customer> queue;
    private List<Customer> servedCustomers;

    public Market() {
        queue = new LinkedList<>();
        servedCustomers = new ArrayList<>();
    }

    @Override
    public void takeInQueue(Customer customer) {
        queue.add(customer);
        System.out.println(customer.getName() + " added to queue.");
    }

    @Override
    public void releaseFromQueue() {
        Customer customer = queue.poll();
        if (customer != null) {
            servedCustomers.add(customer);
            System.out.println(customer.getName() + " released from queue.");
        }
    }

    @Override
    public void acceptOrder(Customer customer) {
        System.out.println("Order accepted from " + customer.getName());
    }

    @Override
    public void releaseOrder(Customer customer) {
        System.out.println("Order released to " + customer.getName());
    }

    @Override
    public void update() {
        if (!queue.isEmpty()) {
            Customer customer = queue.peek();
            acceptOrder(customer);
            releaseOrder(customer);
            releaseFromQueue();
        } else {
            System.out.println("Queue is empty, nothing to update.");
        }
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        // Testing HotDrinksVendingMachine
        HotDrinkWithTemperature coffee = new HotDrinkWithTemperature("Coffee", 250, 80);
        HotDrinkWithTemperature tea = new HotDrinkWithTemperature("Tea", 200, 70);
        HotDrinkWithTemperature hotChocolate = new HotDrinkWithTemperature("Hot Chocolate", 300, 85);

        HotDrinksVendingMachine vendingMachine = new HotDrinksVendingMachine();
        vendingMachine.addProduct(coffee);
        vendingMachine.addProduct(tea);
        vendingMachine.addProduct(hotChocolate);

        HotDrinkWithTemperature requestedDrink = vendingMachine.getProduct("Tea", 200, 70);
        if (requestedDrink != null) {
            System.out.println("Dispensing: " + requestedDrink);
        } else {
            System.out.println("Product not found.");
        }

        // Testing Market
        Market market = new Market();
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");

        market.takeInQueue(customer1);
        market.takeInQueue(customer2);

        market.update();
        market.update();
        market.update();  // This should show that the queue is empty
    }
}
