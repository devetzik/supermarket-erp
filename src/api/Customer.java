package api;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    String fName, lName;
    HashMap<Product, Integer> shoppingCart = new HashMap<>();
    String[][] pr =new String[products.size()][products.size()];
    double total=0;

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
                            if (newQty>i.qty){
                                System.out.println("Δεν υπάρχει διαθέσιμο απόθεμα, διαλέξτε μια ποσότητα <"+ i.qty);
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


    // Μέθοδος για τον υπολογισμό του συνολικού κόστους του καλαθιού

    public double getTotal(){
        total=0;
        for (Product i : shoppingCart.keySet()){
            total+=(i.getPrice() * shoppingCart.get(i));
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
            shoppingCart.clear();
        }
    }


    // Μέθοδος για την προσπέλαση του ιστορικού παραγγελιών

    public void viewOrderHistory(Customer customer) throws IOException, ClassNotFoundException {
        ArrayList<Order> tmp=new ArrayList<>();
        boolean flag=false;
        ArrayList<Order> orderHistory=util.orderHistoryLoader();
        for (Order i : orderHistory) {
            if (i.username.equals(customer.getUsername())) {
                flag = true;
                tmp.add(i);
            }
        }
        if (flag){
            System.out.println("Ιστορικό παραγγελιών του χρήστη "+ customer.getUsername());
            for (Order i : tmp){
                System.out.println("Ημερομηνία παραγγελίας: "+i.datetime);
                System.out.println("Προϊόντα και ποσότητες παραγγελίας: \n");
                for (int j=0; j< i.pr.length;j++){
                    if (i.pr[j][0] != null) {
                        System.out.println(i.pr[j][1] + " x " + i.pr[j][0]);
                    }
                }
                System.out.println("Συνολικό κόστος: "+i.getTotal()+"€\n");
            }
        }
        if (!flag){
            System.out.println("Δεν βρέθηκε ιστορικό παραγγελιών για τον χρήστη "+customer.getUsername());
        }
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
            if (p.title.equals(title)){
                shoppingCart.put(p,qty);
                break;
            }
        }
     }
}