package api;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    private String fName, lName;
    private static HashMap<Product, Integer> shoppingCart = new HashMap<>();
    private ArrayList<Product> products= util.productsLoader();
    private String[][] pr =new String[products.size()][products.size()];

    // Κατασκευαστής του αντικειμένου api.Customer

    public Customer(String username, String password, String fName, String lName) throws IOException, ClassNotFoundException {
        super(username, password);
        this.fName=fName;
        this.lName=lName;
    }


    // Τρέχουσα κατάσταση του καλαθιού

    public void viewShoppingCart(User currentUser) throws IOException{
        Scanner scanner=new Scanner(System.in);
        if (shoppingCart.isEmpty()){
            System.out.println("Το καλάθι είναι άδειο");
        }else {
            int j=0;
            for (Product i : shoppingCart.keySet()) {
                System.out.println(shoppingCart.get(i)+" x "+i.getUnit()+" "+ i.getTitle() + " Κόστος: " + String.format("%.2f", shoppingCart.get(i) * i.getPrice()) + "€" + "("+j+")");
                j++;
            }
            System.out.println("\nΣυνολικό κόστος καλαθιού: "+ String.format("%.2f",getTotal() )+"€");
            System.out.println("\nΟλοκλήρωση παραγγελίας (0) / Επεξεργασία καλαθιού (1)");
            int x=scanner.nextInt();
            if (x==0){
                confirmOrder(currentUser);
            } else if (x==1) {
                System.out.println("Διαγραφή προϊόντος από το καλάθι (0) / Αλλαγή επιλεγμένης ποσότητας (1)");
                x=scanner.nextInt();
                if (x==0){
                    System.out.println("Επιλέξτε προϊόν προς διαγραφή");
                    int d=scanner.nextInt();
                    j=0;
                    for (Product i: shoppingCart.keySet()){
                        if (d==j){
                            shoppingCart.remove(i);
                            System.out.println("Επιτυχής διαγραφή προϊόντος");
                            break;
                        }
                        j++;
                    }
                }else if (x==1){
                    System.out.println("Επιλέξτε προϊόν");
                    int d=scanner.nextInt();
                    j=0;
                    System.out.println("Εισάγετε την νέα ποσότητα");
                    int newQty= scanner.nextInt();
                    for (Product i: shoppingCart.keySet()){
                        if (d==j){
                            if (newQty>i.getQty()){
                                System.out.println("Δεν υπάρχει διαθέσιμο απόθεμα, διαλέξτε μια ποσότητα <"+ i.getQty());
                            }else {
                                shoppingCart.replace(i, newQty);
                                System.out.println("Επιτυχής αλλαγή ποσότητας");
                            }
                        }
                    }
                }
            }
        }
    }


    // Μέθοδος για την προσθήκη προϊόντος στο καλάθι, με έλεγχο έγκυρης εκχώρησης και διαθεσιμότητας

    public void addToShoppingCart(Product product, int posotita){
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
        boolean flag=false;
        ArrayList<Order> orderHistory=util. orderHistoryLoader();
        for (Order i : orderHistory) {
            if (i.getUsername().equals(customer.getUsername())) {
                flag = true;
                tmp.add(i);
            }
        }
        if (flag){
            System.out.println("Ιστορικό παραγγελιών του χρήστη "+ customer.getUsername());
            for (Order i : tmp){
                System.out.println("Ημερομηνία παραγγελίας: "+i.getDatetime());
                System.out.println("Προϊόντα και ποσότητες παραγγελίας: \n");
                for (int j=0; j< i.getPr().length;j++){
                    if (i.getPr()[j][0] != null) {
                        System.out.println(i.getPr()[j][1] + " x " + i.getPr()[j][0]);
                    }
                }
                System.out.println("Συνολικό κόστος: "+i.getTotal()+"€\n");
            }
        }
        if (!flag){
            System.out.println("Δεν βρέθηκε ιστορικό παραγγελιών για τον χρήστη "+customer.getUsername());
        }
        return tmp;
    }

    @Override
    public void viewProduct(Product product) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(product.getDetails());
        System.out.println("Προσθήκη στο καλάθι (1)");
        int x=scanner.nextInt();
        if (x==1){
            System.out.println("Εισάγετε τα τεμάχια");
            int qty= scanner.nextInt();
            addToShoppingCart(product,qty);
        }
    }

    public String getfName(){
        return fName;
    }

    public String getlName(){
        return lName;
    }

    public HashMap<Product,Integer> getShoppingCart(){
        return shoppingCart;
    }
     public void updateCartQty(String title, int qty){
        for (Product p: shoppingCart.keySet()){
            if (p.getTitle().equals(title)){
                shoppingCart.put(p,qty);
                break;
            }
        }
     }


     public void removeFromCart(String title){
        HashMap<Product,Integer> sctmp=new HashMap<>();
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