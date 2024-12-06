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
        ArrayList<Product> searchResults= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Αναζήτηση βάσει τίτλου (1)\nΑναζήτηση βάσει κατηγορίας (2)\nΠροβολή όλων των προϊόντων (3)");
        int s=scanner.nextInt();
        if (s==1) {
            System.out.println("Πληκτρολογήστε τον τίτλο του προϊόντος προς αναζήτηση");
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
                if (cat[x][0]!=null) {
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
        } else if (s==3) {
            for (int i=0;i<products.size();i++){
                System.out.println(products.get(i).getTitle()+"("+i+")");
            }
            int y =scanner.nextInt();
            currnentUser.viewProduct(products.get(y));
        }
        if (s!=3) {
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
    }

    public abstract void viewProduct(Product product) throws IOException;


    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}