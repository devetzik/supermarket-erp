package api;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable {

    Utilities util=new Utilities();
    private String username, password;
    String [][] cat= util.catLoader();
    ArrayList<Product> products=util.productsLoader();



    // Κατασκευαστής αντικειμένου User

    public User(String username, String password) throws IOException {
        this.username=username;
        this.password=password;
    }





    // Μέθοδος για την αναζήτηση προϊόντων

    public void productSearch(User currnentUser) throws IOException {
        ArrayList<Product> searchResults = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Πληκτρολογήστε τον τίτλο του προϊόντος προς αναζήτηση");
        String title = scanner.nextLine();

        if (title.isBlank()) {
            System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
            for (int x = 0; x < cat.length; x++) {
                if (cat[x][0] != null) {
                    System.out.println(cat[x][0] + "(" + x + ")");
                }
            }
            System.out.println("Καμία κατηγορία (100)");
            int x = scanner.nextInt();
            if (x != 100) {
                String category = cat[x][0];
                System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
                for (int y = 1; y < cat[x].length; y++) {
                    if (cat[x][y] != null) {
                        System.out.println(cat[x][y] + "(" + y + ")");
                    }
                }
                System.out.println("Καμία υποκατηγορία (100)");
                int y = scanner.nextInt();
                if (y == 100) {
                    for (Product p : products) {
                        if (p.getCategory().equals(category)) {
                            searchResults.add(p);
                        }
                    }
                } else {
                    String subcategory = cat[x][y];
                    for (Product p : products) {
                        if (p.getSubcategory().equals(subcategory)) {
                            searchResults.add(p);
                        }
                    }
                }
            } else {
                searchResults = products;
            }
        } else {
            System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
            for (int x = 0; x < cat.length; x++) {
                if (cat[x][0] != null) {
                    System.out.println(cat[x][0] + "(" + x + ")");
                }
            }
            System.out.println("Καμία κατηγορία (100)");
            int x = scanner.nextInt();
            if (x != 100) {
                String category = cat[x][0];
                System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
                for (int y = 1; y < cat[x].length; y++) {
                    if (cat[x][y] != null) {
                        System.out.println(cat[x][y] + "(" + y + ")");
                    }
                }
                System.out.println("Καμία υποκατηγορία (100)");
                int y = scanner.nextInt();
                if (y == 100) {
                    for (Product p : products) {
                        if (p.getCategory().equals(category) && p.title.contains(title)) {
                            searchResults.add(p);
                        }
                    }
                } else {
                    String subcategory = cat[x][y];
                    for (Product p : products) {
                        if (p.getSubcategory().equals(subcategory) && p.title.contains(title)) {
                            searchResults.add(p);
                        }
                    }
                }
            } else {
                for (Product i : products) {
                    if (i.getTitle().contains(title)) {
                        searchResults.add(i);
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
            currnentUser.viewProduct(searchResults.get(y));
        } else {
            System.out.println("Δεν βρέθηκαν προϊόντα για αυτή την αναζήτηση");
        }
    }


    public abstract void viewProduct(Product product) throws IOException;


    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String[] getCategories(){
        int counter=0;
        for (int i=0; i<cat.length;i++){
            if (cat[i][0]!=null){
                counter++;
            }
        }
        String[] categories=new String[counter+1];
        categories[0]="Όλες οι κατηγορίες";
        for (int i=0;i<counter;i++){
            categories[i+1]=cat[i][0];
        }
        return categories;
    }

    public String[] getSubcategories(String category){
        int counter=0;
        for (int i=0; i< cat.length;i++){
            if (cat[i][0]!=null) {
                if (cat[i][0].equals(category)) {
                    for (int j = 1; j < cat[i].length; j++) {
                        if (cat[i][j]!=null) {
                            counter++;
                        }
                    }
                }
            }
        }
        String[] subcategories=new String[counter+1];
        subcategories[0]="Όλες οι υποκατηγορίες";
        for (int i=0; i< cat.length;i++){
            if (cat[i][0]!=null){
                if (cat[i][0].equals(category)) {
                    for (int j = 0; j < counter; j++) {
                        subcategories[j + 1] = cat[i][j + 1];
                    }
                }
            }
        }
        return subcategories;
    }


    public String[] getProductsNames(){
        String[] productsNames=new String[products.size()];
        for (int i=0;i<products.size();i++){
            productsNames[i]=products.get(i).getTitle();
        }
        return productsNames;
    }

    public Product getProduct(String productTitle){
        Product product=null;
        for (Product i:products){
            if (i.getTitle().equals(productTitle)){
                product=i;
            }
        }
        return product;
    }
}