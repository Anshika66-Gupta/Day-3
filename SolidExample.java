// Single Responsibility Principle (SRP)
class Product {
    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class ProductRepository {
    public void save(Product product) {
        // Logic to save product in a database
        System.out.println("Product saved: " + product.getName());
    }
}

// Open/Closed Principle (OCP)
abstract class Discount {
    public abstract double apply(double price);
}

class PercentageDiscount extends Discount {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double apply(double price) {
        return price - (price * (percentage / 100));
    }
}

class FixedAmountDiscount extends Discount {
    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double price) {
        return price - amount;
    }
}

// Liskov Substitution Principle (LSP)
class Payment {
    public void pay(double amount) {
        System.out.println("Paid: " + amount);
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid with Credit Card: " + amount);
    }
}

class PayPalPayment extends Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid with PayPal: " + amount);
    }
}

// Interface Segregation Principle (ISP)
interface Printable {
    void printInvoice();
}

class Invoice implements Printable {
    @Override
    public void printInvoice() {
        System.out.println("Invoice printed.");
    }
}

// Dependency Inversion Principle (DIP)
interface NotificationService {
    void sendNotification(String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}

class Order {
    private NotificationService notificationService;

    public Order(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(Product product) {
        System.out.println("Order placed for: " + product.getName());
        notificationService.sendNotification("Order placed for " + product.getName());
    }
}

// Main Class
public class SolidExample {
    public static void main(String[] args) {
        // SRP
        Product product = new Product(1, "Laptop");
        ProductRepository productRepository = new ProductRepository();
        productRepository.save(product);

        // OCP
        Discount discount = new PercentageDiscount(10);
        System.out.println("Discounted Price: " + discount.apply(1000));

        Discount fixedDiscount = new FixedAmountDiscount(200);
        System.out.println("Discounted Price: " + fixedDiscount.apply(1000));

        // LSP
        Payment payment = new CreditCardPayment();
        payment.pay(800);

        payment = new PayPalPayment();
        payment.pay(800);

        // ISP
        Invoice invoice = new Invoice();
        invoice.printInvoice();

        // DIP
        NotificationService emailService = new EmailNotificationService();
        Order order = new Order(emailService);
        order.placeOrder(product);
    }
}
