package api;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    private Utilities util=new Utilities();
    private String fName, lName;
    private static HashMap<Product, Double> shoppingCart = new HashMap<>();
    private ArrayList<Product> products= util.productsLoader();
    private String[][] pr =new String[products.size()][products.size()];


    // Κατασκευαστής του αντικειμένου api.Customer

    public Customer(String username, String password, String fName, String lName) throws IOException, ClassNotFoundException {
        super(username, password);
        this.fName=fName;
        this.lName=lName;
    }



    // Μέθοδος για την προσθήκη προϊόντος στο καλάθι, με έλεγχο έγκυρης εκχώρησης και διαθεσιμότητας

    public void addToShoppingCart(Product product, double posotita){
        shoppingCart.put(product, posotita);
    }


    // Μέθοδος για τον υπολογισμό του συνολικού κόστους του καλαθιού

    public double getTotal(){
        double total = 0;
        for (Product i : shoppingCart.keySet()){
            total +=(i.getPrice() * shoppingCart.get(i));
        }
        return total;
    }


    //Ολοκλήρωση παραγγελίας, εγγραφή στο ιστορικό και αφαίρεση αποθέματος

    public void confirmOrder(User currentUser) throws IOException{
        int k=0;
        for (Product i: shoppingCart.keySet()){
            pr[k][0]=i.getTitle();
            pr[k][1]=shoppingCart.get(i).toString();
            k++;
        }
        Order order = new Order(currentUser.getUsername(), pr, LocalDate.now().toString(), getTotal());
        util.orderWriter(order);

        for (Product i : shoppingCart.keySet()){
            Product newP=i;
            newP.setQty(i.getQty()-shoppingCart.get(i));

            util.productsRemover(i);
            util.productsWriter(newP);

        }
        shoppingCart.clear();
    }


    // Μέθοδος για την προσπέλαση του ιστορικού παραγγελιών

    public ArrayList viewOrderHistory(Customer customer) throws IOException, ClassNotFoundException {
        ArrayList<Order> tmp=new ArrayList<>();
        ArrayList<Order> orderHistory=util. orderHistoryLoader();
        for (Order i : orderHistory) {
            if (i.getUsername().equals(customer.getUsername())) {
                tmp.add(i);
            }
        }
        return tmp;
    }

    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public HashMap<Product,Double> getShoppingCart(){
        return shoppingCart;
    }
     public void updateCartQty(String title, double qty){
        for (Product p: shoppingCart.keySet()){
            if (p.getTitle().equals(title)){
                shoppingCart.put(p,qty);
                break;
            }
        }
     }


     public void removeFromCart(String title){
        HashMap<Product,Double> sctmp=new HashMap<>();
        for (Product p:shoppingCart.keySet()){
            sctmp.put(p,shoppingCart.get(p));
        }

        //shoppingCart.clear();

        for (Product p: sctmp.keySet()){
            if (p.getTitle().equals(title)) {
                shoppingCart.remove(p);
            }
        }




    }
}