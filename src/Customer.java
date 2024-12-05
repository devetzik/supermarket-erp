import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Customer extends User implements Serializable {

    String fName, lName;
    HashMap<Product, Integer> shoppingCart = new HashMap();
    ArrayList<Order> orderHistory;
    String pr[][]=new String[products.size()][products.size()];

    double total=0;

    // Κατασκευαστής του αντικειμένου Customer

    public Customer(String username, String password, String fName, String lName) throws IOException, ClassNotFoundException {
        super(username, password);
        this.fName=fName;
        this.lName=lName;
    }


    // Τρέχουσα κατάσταση του καλαθιού

    public void viewShoppingCart(User currentUser) throws IOException, ClassNotFoundException {
        Scanner scanner=new Scanner(System.in);
        if (shoppingCart.isEmpty()){
            System.out.println("Το καλάθι είναι άδειο");
        }else {
            for (Product i : shoppingCart.keySet()) {
                System.out.println(shoppingCart.get(i)+" x "+ i.getTitle() + " Κόστος: " + (shoppingCart.get(i) * i.getPrice()) + "€");
            }
            System.out.println("\nΣυνολικό κόστος καλαθιού: "+ getTotal()+"€");
            System.out.println("\nΟλοκλήρωση παραγγελίας (0)");
            int x=scanner.nextInt();
            if (x==0){
                confirmOrder(currentUser);
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
        total=0;
        for (Product i : shoppingCart.keySet()){
            total+=(i.getPrice() * shoppingCart.get(i));
        }
        return total;
    }


    //Ολοκλήρωση παραγγελίας, εγγραφή στο ιστορικό και αφαίρεση αποθέματος

    public void confirmOrder(User currentUser) throws IOException, ClassNotFoundException {

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
    }


    // Μέθοδος για την προσπέλαση του ιστορικού παραγγελιών

    public void viewOrderHistory(Customer customer) throws IOException, ClassNotFoundException {
        boolean flag=false;
        orderHistory=util.orderHistoryLoader();
        System.out.println("Ιστορικό παραγγελιών του χρήστη "+ customer.getUsername());
        for (Order i : orderHistory){
            if (i.username.equals(customer.getUsername())){
                flag=true;
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
            String spare=scanner.nextLine();
            addToShoppingCart(product,qty);
        }

    }
}