import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User implements Serializable {

    ArrayList<Product> products;
    ArrayList<Order> orderHistory;
    HashMap<Product, Integer> sales;

    ArrayList<String> productCategories= new ArrayList<>();
    String [][] cat;



    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password){
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public ArrayList<Product> addProduct(ArrayList<Product> products,String cat [][]) throws IOException, ClassNotFoundException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Εισάγετε τον τίτλο του προϊόντος");
        String title=scanner.nextLine();
        System.out.println("Εισάγετε την περιγραφή του προϊόντος");
        String description=scanner.nextLine();
        System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
        int tmp=0;
        for (int x=0; x< cat.length; x++){
            if (cat[x][tmp]!=null) {
                System.out.println(cat[x][0] + "(" + x + ")");
            }
        }
        int x=scanner.nextInt();
        String category= cat[x][0];
        System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
        for (int y=0; y<cat[x].length; y++){
            if (cat[x][y]!=null) {
                System.out.println(cat[x][y] + "(" + y + ")");
            }
        }
        int y= scanner.nextInt();
        String subcategory=cat[x][y];
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
        }
        return products;
    }




    public ArrayList<Product> editProduct(ArrayList<Product> products){


        return products;
    }


    public void adminStats(ArrayList<Product> products, ArrayList<Order> orderHistory, HashMap<Product, Integer> sales){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Επιλέξτε λειτουργία:\nΠροϊόντα με εξαντλημένο απόθεμα (1)\nΠροϊόντα που εμφανίστηκαν στις περισσότερες παραγγελίες (2)");
        int x=scanner.nextInt();
        if (x==1){
            System.out.println("Τα προϊόντα με εξαντλημένο απόθεμα είναι: \n");
            for (Product i : products){
                if (i.getQty()==0){
                    System.out.println(i.getTitle() + "\n");
                }
            }
        } else if (x==2) {
            for (Order i : orderHistory){
                for (Product p : i.getShoppingCart().keySet()){
                    sales.put(p, sales.get(p)+1);
                }
            }
        }
    }


}
