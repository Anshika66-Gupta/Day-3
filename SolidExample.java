
import javax.management.Notification;

//SRP
class Product{
    private int id;
    private String name;
    
    public Product(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
class ProductRepository{
    public void save(Product product){
        System.out.println("Product saved:" + product.getName());
    }
}

//OCP 
abstract class Discount{
    public abstract double apply(double price);
}
class PercentageDiscount extends Discount{
    private double percentage;
    
    public PercentageDiscount(double percentage){
        this.percentage = percentage;
    }
    @Override
    public double apply(double price){
        return price - (price * (percentage / 100));
    }
}

class FixedAmountDiscount extends Discount{
    private double amount;
    public FixedAmountDiscount(double amount){
        this.amount = amount;
    }
    @Override
    public double apply(double price){
        return price - amount;
    }
}

//LSP 

class Payment{
    public void pay(double amount){
        System.out.println("Paid:" +amount);
    }
}
class CreditCardPayment extend Payment{
    @Override 
    public void pay(double amount){
        System.out.println("Pay with credit card:" + amount);
    }
}
class PayPalPayment extends Payment{
    @Override 
    public void pay(double amount){
        System.out.println("Pay with PayPal:" + amount);
    }
}

//ISP
interface Printable{
    void printInvoice();
}
class Invoice implements Printable{
 @Override
 public void printInvoice(){
    System.out.println("Invoice Printed");
 }   
}

//DIP
interface NotificationService{
    void sendNotification(String message);
}

class EmailNotificationService implements NotificationService{
    @Override
    public void sendNotification(String message){
        System.out.println("Email Sent:" + message);
    }
}
class Order{
    private NotificationService notificationService;
    
    public Order(NotificationService notificationService) 
    {
        this.notificationService = notificationService;
    }
    public void placeOrder(Product product){
        System.out.println("Order placed for: " + product.getName());
        notificationService.sendNotification("Order placed" + product.getName());
    }
}
