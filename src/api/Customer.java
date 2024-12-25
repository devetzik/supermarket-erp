package api;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User implements Serializable {
    private final String fName;
    private final String lName;
    private static final HashMap<Product, Double> shoppingCart = new HashMap<>();
    private final ArrayList<Product> products= Utilities.productsLoader();
    private final String[][] pr =new String[products.size()][products.size()];



    /**
     * Κατασκευαστής: Δημιουργεί έναν πελάτη με τις δεδομένες παραμέτρους
     *
     * @param username το όνομα χρήστη του χρήστη
     * @param password ο κωδικός του χρήστη
     * @param fName το όνομα του χρήστη
     * @param lName το επώνυμο του χρήστη
     */
    public Customer(String username, String password, String fName, String lName){
        super(username, password);
        this.fName=fName;
        this.lName=lName;
    }


    /**
     * Προσθέτει ένα προϊόν στο καλάθι.
     *
     * @param product  το επιλεγμένο προϊόν
     * @param posotita  η επιλεγμένη ποσότητα του επιλεγμένου προϊόντος
     */
    public void addToShoppingCart(Product product, double posotita){
        shoppingCart.put(product, posotita);
    }



    /**
     * Υπολογίζει και επιστρέφει το συνολικό κόστος της παραγγελίας του χρήστη.
     *
     * @return το συνολικό κόστος της παραγγελίας
     */
    public double getTotal(){
        double total = 0;
        for (Product i : shoppingCart.keySet()){
            total +=(i.getPrice() * shoppingCart.get(i));
        }
        return total;
    }



    /**
     * Ολοκληρώνει την παραγγελία ενός χρήστη, καταγράφει την παραγγελία στο ιστορικό και ενημερώνει τις διαθέσιμες
     * ποσότητες των επιλεγμένων προϊόντων.
     *
     * @param customer  ο χρήστης που ολοκληρώνει την παραγγελία
     */
    public void confirmOrder(Customer customer){
        int k=0;
        for (Product i: shoppingCart.keySet()){
            pr[k][0]=i.getTitle();
            pr[k][1]=shoppingCart.get(i).toString();
            k++;
        }
        Order order = new Order(customer.getUsername(), pr, LocalDate.now().toString(), getTotal());
        Utilities.orderWriter(order);

        for (Product i : shoppingCart.keySet()){
            Product newP=i;
            newP.setQty(i.getQty()-shoppingCart.get(i));

            Utilities.productsRemover(i);
            Utilities.productsWriter(newP);

        }
        shoppingCart.clear();
    }



    /**
     * Ελέγχει ολόκληρο το ιστορικό τον παραγγελιών και βρίσκει τις παραγγελίες που έχουν γίνει από έναν συγκεκριμένο
     * πελάτη.
     *
     * @param customer  ο πελάτης του οποίου το ιστορικό ψάχνουμε
     * @return Arraylist tmp, τη λίστα με τις παραγγελίες του χρήστη
     */
    public ArrayList<Order> getOrderHistory(Customer customer){
        ArrayList<Order> tmp=new ArrayList<>();
        ArrayList<Order> orderHistory= null;
        orderHistory = Utilities. orderHistoryLoader();
        for (Order i : orderHistory) {
            if (i.getUsername().equals(customer.getUsername())) {
                tmp.add(i);
            }
        }
        return tmp;
    }


    /**
     * Getter για το όνομα του χρήστη.
     */
    public String getfName(){
        return fName;
    }


    /**
     * Getter για το επώνυμο του χρήστη
     */
    public String getlName(){
        return lName;
    }


    /**
     * Getter για το καλάθι αγορών του χρήστη.
     */
    public HashMap<Product,Double> getShoppingCart(){
        return shoppingCart;
    }


    /**
     * Ενημερώνει την επιλεγμένη ποσότητα ενός προϊόντος στο καλάθι αγορών του χρήστη.
     *
     * @param title ο τίτλος του προϊόντος
     * @param qty η νέα επιλεγμένη ποσότητα του προϊόντος
     */
     public void updateCartQty(String title, double qty){
        for (Product p: shoppingCart.keySet()){
            if (p.getTitle().equals(title)){
                shoppingCart.put(p,qty);
                break;
            }
        }
     }


    /**
     * Αφαιρεί ένα προϊόν από το καλάθι του χρήστη.
     *
     * @param title ο τίτλος του προϊόντος
     */
    public void removeFromCart(String title){
        HashMap<Product,Double> sctmp=new HashMap<>();
        for (Product p:shoppingCart.keySet()){
            sctmp.put(p,shoppingCart.get(p));
        }

        for (Product p: sctmp.keySet()){
            if (p.getTitle().equals(title)) {
                shoppingCart.remove(p);
            }
        }
    }
}