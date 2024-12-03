import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User implements Serializable {

    String fName, lName;
    HashMap<Product, Integer> shoppingCart = new HashMap();
    ArrayList<Order> orderHistory;

    double total=0;

    // Κατασκευαστής του αντικειμένου Customer

    public Customer(String username, String password, String fName, String lName) throws IOException, ClassNotFoundException {
        super(username, password);
        this.fName=fName;
        this.lName=lName;
    }


    // Τρέχουσα κατάσταση του καλαθιού

    public void shoppingCart(){
        for (Product i : shoppingCart.keySet()){
            System.out.println("Τίτλος: "+i.getTitle()+"\nΠοσότητα: "+shoppingCart.get(i)+"Συνολικό κόστος: "+(shoppingCart.get(i)* i.getPrice())+"€");
        }
        System.out.println("Συνολικό κόστος καλαθιού: "+ getTotal()+"€");
    }


    // Μέθοδος για την προσθήκη προϊόντος στο καλάθι, με έλεγχο έγκυρης εκχώρησης και διαθεσιμότητας

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
            System.out.println("Επιτυχής προσθήκη στο καλάθι");
        }
    }


    // Μέθοδος για την αλλαγή της ποσότητας ενός προϊόντος του καλαθιού

    public void changeProductQuantity(Product product, int posotita){
        shoppingCart.replace(product, posotita);
    }


    // Μέθοδος για την αφαίρεση ενός προϊόντος από το καλάθι

    public void removeFromShoppingCart(Product product){
        shoppingCart.remove(product);
    }


    // Μέθοδος για τον υπολογισμό του συνολικού κόστους του καλαθιού

    public double getTotal(){
        for (Product i : shoppingCart.keySet()){
            total+=(i.getPrice() * shoppingCart.get(i));
        }
        return total;
    }


    //Ολοκλήρωση παραγγελίας, εγγραφή στο ιστορικό και αφαίρεση αποθέματος

    public void confirmOrder() throws IOException, ClassNotFoundException {
        Order order = new Order((Customer) currnentUser, shoppingCart,LocalDateTime.now(), getTotal());
        orderHistory.add(order);


        for (Product i : shoppingCart.keySet()){
            i.setQty(i.getQty()-shoppingCart.get(i));
        }
    }


    // Μέθοδος για την προσπέλαση του ιστορικού παραγγελιών

    public void viewOrderHistory(Customer customer) throws IOException, ClassNotFoundException {
        boolean flag=false;
        orderHistory=util.orderHistoryLoader();
        System.out.println("Ιστορικό παραγγελιών του χρήστη "+ customer.getUsername());
        for (Order i : orderHistory){
            if (i.getCustomer()==customer){
                flag=true;
                System.out.println("Ημερομηνία παραγγελίας: "+i.getDateTime()+"\n");
                System.out.println("Προϊόντα και ποσότητες παραγγελίας: "+i.getShoppingCart()+"\n");
                System.out.println("Συνολικό κόστος: "+i.getTotal()+"€\n");
            }
        }
        if (!flag){
            System.out.println("Δεν βρέθηκε ιστορικό παραγγελιών για τον χρήστη "+customer.getUsername());
        }
    }

    @Override
    public void productSearch() {

    }
}