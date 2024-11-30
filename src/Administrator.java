import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User implements Serializable {

    ArrayList<Product> products;
    ArrayList<Order> orderHistory;
    HashMap<Product, Integer> sales;

    ArrayList<String> productCategories= new ArrayList<>();

    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password){
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public void addProduct(String category, String subcategory) throws IOException, ClassNotFoundException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Εισάγετε τον τίτλο του προϊόντος");
        String title=scanner.nextLine();
        System.out.println("Εισάγετε την περιγραφή του προϊόντος");
        String description=scanner.nextLine();
        System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
        System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
        System.out.println("Εισάγετε την τιμή του προϊόντος");
        double price=scanner.nextDouble();
        System.out.println("Εισάγετε την διαθέσιμη ποσότητα του προϊόντος");
        int qty=scanner.nextInt();


        if (title.isBlank() || description.isBlank() || category.isBlank() || subcategory.isBlank() || price<0 || qty<0){
            System.out.println("Λανθασμένη εισαγωγή");
        }
        else {
            Product p = new Product(title, description, category, subcategory, price, qty);
            products.add(p);

            ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("products.txt"));
            writer.writeObject(products);
            writer.close();

            ObjectInputStream reader= new ObjectInputStream(new FileInputStream("products.txt"));
            products= (ArrayList<Product>) reader.readObject();
            reader.close();
        }
    }


    // Μέθοδος για την εύρεση προϊόντων με μηδενικό απόθεμα

    public void outOfStock(){
        System.out.println("Τα προϊόντα με εξαντλημένο απόθεμα είναι: \n");
        for (Product i : products){
            if (i.getQty()==0){
                System.out.println(i.getTitle() + "\n");
            }
        }
    }


    // Μέθοδος για την καταγραφή του πλήθους των παραγγελιών ανά προϊόν

    public void salesMap(){
        for (Order i : orderHistory){
            for (Product p : i.getShoppingCart().keySet()){
                sales.put(p, sales.get(p)+1);
            }
        }
    }


    // Μέθοδος για την ταξινόμιση των προϊόντων με βάση το πλήθος των παραγγελιών στις οποίες συμπεριλήφθηκαν

    /*public void salesSort(){

    }*/


}
