import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User implements Serializable {

    Utilities util=new Utilities();
    ArrayList<Product> products;

    ArrayList<Order> orderHistory;
    HashMap<Product, Integer> sales;
    String [][] cat= util.catLoader();



    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }

    @Override
    public void productSearch() {
        ArrayList<Product> searchResults= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Αναζήτηση βάσει τίτλου (1)\nΑναζήτηση βάσει κατηγορίας (2)");
        int s=scanner.nextInt();
        if (s==1) {
            System.out.println("Πληκτρολογίστε τον τίτλο του προϊόντος προς αναζήτηση");
            String spare=scanner.nextLine();
            String title= scanner.nextLine();
            for (Product i : products) {
                if (i.getTitle().contains(title)) {
                    searchResults.add(i);
                }
            }
        }else if (s==2){
            System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
            for (int x=0; x< cat.length; x++){
                if (cat[x]!=null) {
                    System.out.println(cat[x][0] + "(" + x + ")");
                }
            }
            int x=scanner.nextInt();
            String category= cat[x][0];
            System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
            System.out.println("Καμία υποκατηγορία (100)");
            for (int y=1; y<cat[x].length; y++){
                if (cat[x][y]!=null) {
                    System.out.println(cat[x][y] + "(" + y + ")");
                }
            }
            int y= scanner.nextInt();
            if (y==100){
                for (Product p : products){
                    if (p.getCategory().equals(category)){
                        searchResults.add(p);
                    }
                }
            }else {
                String subcategory=cat[x][y];
                for (Product p : products) {
                    if (p.getSubcategory().equals(subcategory)){
                        searchResults.add(p);
                    }
                }
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println("Επιλέξτε προϊόν:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(searchResults.get(i).getTitle() + "(" + i + ")");
            }
            int y = scanner.nextInt();
            System.out.println(searchResults.get(y).getDetails());
        }else {
            System.out.println("Δεν βρέθηκαν προϊόντα για αυτή την αναζήτηση");
        }
        scanner.close();
    }



    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public void addProduct() throws IOException, ClassNotFoundException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Εισάγετε τον τίτλο του προϊόντος");
        String title=scanner.nextLine();
        System.out.println("Εισάγετε την περιγραφή του προϊόντος");
        String description=scanner.nextLine();
        System.out.println("Επιλέξτε την κατηγορία του προϊόντος");

        for (int x=0; x< cat.length; x++){
            if (cat[x][0]!=null) {
                System.out.println(cat[x][0] + "(" + x + ")");
            }
        }
        int x=scanner.nextInt();
        String category= cat[x][0];
        System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
        for (int y=1; y<cat[x].length; y++){
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
            Product product=new Product(title,description,category,subcategory,price,qty);
            util.productsWriter(product);
            System.out.println("Επιτυχής προσθήκη προϊόντος");
        }
        scanner.close();
    }





    public void editProduct(Product product) throws IOException {
        Product newP= product;
        Scanner scanner=new Scanner(System.in);
        products=util.productsLoader();
        System.out.println("Επεξεργασία τίτλου (1)\nΕπεξεργασία περιγραφής (2)\nΑλλαγή κατηγορίας (3)\nΑλλαγή Υποκατηγορίας (4)\nΑλλαγή τιμής (5)\nΑλλαγή ποσότητας αποθέματος (6)");
        int x= scanner.nextInt();

        if (x==1){
            System.out.println("Εισάγετε τον νέο τίτλο");
            String title=scanner.nextLine();
            newP.setTitle(title);
            System.out.println("Επιτυχής αλλαγή τίτλου");
            util.productsRemover(product);
            util.productsWriter(newP);
        }



        scanner.close();
    }


    public void adminStats() throws IOException {
        Scanner scanner= new Scanner(System.in);
        products=util.productsLoader();
        System.out.println("Επιλέξτε λειτουργία:\nΠροϊόντα με εξαντλημένο απόθεμα (1)\nΠροϊόντα που εμφανίστηκαν στις περισσότερες παραγγελίες (2)");
        int x=scanner.nextInt();
        if (x==1){
            ArrayList<Product> noInv=new ArrayList<>();
            for (Product i : products){
                if (i.getQty()==0){
                    noInv.add(i);
                }
            }
            if (noInv.isEmpty()){
                System.out.println("Δεν υπάρχουν προϊόντα με εξαντλημένο απόθεμα");
            }else {
                System.out.println("Τα προϊόντα με εξαντλημένο απόθεμα είναι: \n");
                for (Product j : noInv)
                    System.out.println(j.getTitle());
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
