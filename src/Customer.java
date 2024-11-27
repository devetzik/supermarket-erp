import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User{

    HashMap<Product, Integer> shoppingCart = new HashMap();
    double total=0;

    public Customer(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
    }








    public void addToShoppingCart(Product product, int posotita){
        boolean flag=true;
        if (posotita<0){
            flag=false;
            System.out.println("Μη έγκυρη ποσότητα προϊόντος, εισάγετε τιμή >0");
        }

        if (posotita> product.getQty()){
            flag=false;
            System.out.println("Δεν υπάρχει αρκετό απόθεμα, μέγιστη ποσότητα: " + product.getQty());
        }

        if (flag){
            shoppingCart.put(product, posotita);
        }


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
