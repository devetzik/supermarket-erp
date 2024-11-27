import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User{

    HashMap<Product, Integer> shoppingCart = new HashMap();
    double total=0;

    public Customer(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
    }








    public void addToShoppingCart(Product product, int posotita){
        shoppingCart.put(product, posotita);
    }

    public void changeProductQuantity(Product product, int posotita){
        shoppingCart.replace(product, posotita);
    }

    public void removeFromShoppingCart(Product product){
        shoppingCart.remove(product);
    }

    public double getTotal(){
        for (Product i : shoppingCart.keySet()){
            total+=(i.getPrice() * shoppingCart.get(i));
        }
        return total;
    }





}
