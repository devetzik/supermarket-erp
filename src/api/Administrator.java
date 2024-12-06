package api;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User implements Serializable {
    Utilities util=new Utilities();
    ArrayList<Product> products=util.productsLoader();
    ArrayList<Order> orderHistory=util.orderHistoryLoader();
    HashMap<String, Integer> sales;
    String [][] cat= util.catLoader();


    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public void addProduct() throws IOException {
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
    }


    public void editProduct(Product product) throws IOException {
        Product newP= product;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Επεξεργασία τίτλου (1)\nΕπεξεργασία περιγραφής (2)\nΑλλαγή κατηγορίας (3)\nΑλλαγή Υποκατηγορίας (4)\nΑλλαγή τιμής (5)\nΑλλαγή ποσότητας αποθέματος (6)");
        int x= scanner.nextInt();
        String line;
        double newPrice;
        int newQty;
        if (x==1){
            System.out.println("Εισάγετε τον νέο τίτλο");
            line=scanner.nextLine();
            newP.setTitle(line);
            System.out.println("Επιτυχής αλλαγή τίτλου");
        } else if (x==2) {
            System.out.println("Εισάγετε την νέα περιγραφή");
            line=scanner.nextLine();
            newP.setDescription(line);
            System.out.println("Επιτυχής αλλαγή περιγραφής");
        } else if (x==3) {
            System.out.println("Επιλέξτε κατηγορία:");
            for (int i=0;i< cat.length;i++){
                if (cat[i][0]!=null) {
                    System.out.println(cat[i][0] + "(" + i + ")");
                }
            }
            int i= scanner.nextInt();
            line=cat[i][0];
            newP.setCategory(line);
            System.out.println("Επιλέξτε υποκατηγορία");
            for (int j=1; j<cat[i].length; j++){
                if (cat[i][j]!=null) {
                    System.out.println(cat[i][j] + "(" + j + ")");
                }
            }
            int j= scanner.nextInt();
            line=cat[i][j];
            newP.setSubcategory(line);
            System.out.println("Επιτυχής αλλαγή κατηγορίας-υποκατηγορίας");
        } else if (x==4) {
            System.out.println("Επιλέξτε υποκατηγορία:");
            int c=0;
            for (int i=0;i< cat.length;i++){
                if (cat[i][0].equals(newP.getCategory())){
                    c=i;
                    break;
                }
            }
            for (int j=1; j<cat[c].length; j++){
                if (cat[c][j]!=null) {
                    System.out.println(cat[c][j] + "(" + j + ")");
                }
            }
            int j= scanner.nextInt();
            line=cat[c][j];
            newP.setSubcategory(line);
            System.out.println("Επιτυχής αλλαγή υποκατηγορίας");
        } else if (x==5) {
            System.out.println("Εισάγετε την νέα τιμή");
            newPrice=scanner.nextDouble();
            newP.setPrice(newPrice);
            System.out.println("Επιτυχής αλλαγή τιμής");
        } else if (x==6) {
            System.out.println("Εισάγετε την νέα ποσότητα αποθέματος");
            newQty=scanner.nextInt();
            newP.setQty(newQty);
            System.out.println("Επιτυχής αλλαγή ποσότητας αποθέματος");
        }
        util.productsWriter(newP);
        util.productsRemover(product);
        System.out.println(newP.getDetails());
    }


    public void adminStats() throws IOException {
        Scanner scanner= new Scanner(System.in);
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
            if (orderHistory.isEmpty()){
                System.out.println("Δεν υπάρχουν δεδομένα");
            }else {
                for (Order i : orderHistory) {
                    for (int j = 0; j < i.pr.length; j++) {
                        if (sales.containsKey(i.pr[j][0])) {
                            sales.put(i.pr[j][0], sales.get(i.pr[j][0] + 1));
                        } else {
                            sales.put(i.pr[j][0], 1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void viewProduct(Product product) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println(product.getDetails());
        System.out.println("Επεξεργασία προϊόντος (0)");
        int x=scanner.nextInt();
        if (x==0){
            editProduct(product);
        }
    }
}
