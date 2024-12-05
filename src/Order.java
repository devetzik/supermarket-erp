import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Order{

    String username,datetime;
    String[][] pr;
    Customer customer;
    HashMap<Product,Integer> shoppingCart;
    double total;

    public Order(String username, String[][] pr, String dateTime, double total){
        this.username=username;
        this.pr=pr;
        this.datetime=dateTime;
        this.total=total;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String[][] getPr(){
        return pr;
    }

    public HashMap<Product,Integer> getShoppingCart(){
        return shoppingCart;
    }


    public double getTotal(){
        return total;
    }






}
