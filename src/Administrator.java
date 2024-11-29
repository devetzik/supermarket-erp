import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrator extends User implements Serializable {

    ArrayList<Product> products;
    ArrayList<Order> orderHistory;
    HashMap<Product, Integer> sales;

    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password){
        super(username,password);
    }


    // Μέθοδος για την φόρτωση της λίστας προϊόντων από αρχείο

    public void loadProducts() throws IOException, ClassNotFoundException {
        ObjectInputStream reader= new ObjectInputStream(new FileInputStream("products.txt"));
        products= (ArrayList<Product>) reader.readObject();
        reader.close();
    }


    // Μέθοδος για την φόρτωση του ιστορικού παραγγελιών από αρχείο

    public void loadOrderHistory() throws IOException, ClassNotFoundException {
        ObjectInputStream reader= new ObjectInputStream(new FileInputStream("orderhistory.txt"));
        orderHistory = (ArrayList<Order>) reader.readObject();
        reader.close();
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public void addProduct(String title, String description, String category, String subcategory,double price, int qty) throws IOException, ClassNotFoundException {
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
