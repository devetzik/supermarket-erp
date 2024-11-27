import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Order{

    Customer customer;
    HashMap<Product,Integer> shoppingCart;
    LocalDateTime dateTime;
    double total;

    public Order(Customer customer, HashMap<Product,Integer> shoppingCart, LocalDateTime dateTime, double total){
        this.customer=customer;
        this.shoppingCart=shoppingCart;
        this.dateTime=dateTime;
        this.total=total;
    }

    public Customer getCustomer(){
        return customer;
    }

    public HashMap<Product,Integer> getShoppingCart(){
        return shoppingCart;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public double getTotal(){
        return total;
    }






}
