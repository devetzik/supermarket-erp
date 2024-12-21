package api;

import java.io.*;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private String username, password;
    private static String[][] cat;

    static {
        try {
            cat = Utilities.catLoader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Product> products = Utilities.productsLoader();
    private static String[] categories=new String[30];
    private static String[] subcategories=new String[30];

    // Κατασκευαστής αντικειμένου User

    public User(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
    }


    // Μέθοδος για την αναζήτηση προϊόντων

    public String [] productSearch(String title, String category, String subcategory) throws IOException {
        ArrayList<String> searchResults = new ArrayList<>();
        if (title.isBlank() && category.equals("Όλες οι κατηγορίες")) {
            for (Product p: products){
                searchResults.add(p.getTitle());
            }
        } else if (title.isBlank() && !category.equals("Όλες οι κατηγορίες") && subcategory.equals("Όλες οι υποκατηγορίες")) {
            for (Product p : products) {
                if (p.getCategory().equals(category)) {
                    searchResults.add(p.getTitle());
                }
            }
        } else if (title.isBlank() && !category.equals("Όλες οι κατηγορίες") && !subcategory.equals("Όλες οι υποκατηγορίες")) {
            for (Product p : products) {
                if (p.getSubcategory().equals(subcategory)) {
                    searchResults.add(p.getTitle());
                }
            }
        } else if (!title.isBlank() && category.equals("Όλες οι κατηγορίες")){
            for (Product p : products) {
                if (p.getTitle().contains(title) ||  p.getTitle().toLowerCase().contains(title.toLowerCase()) || p.getTitle().toUpperCase().contains(title.toUpperCase())) {
                    searchResults.add(p.getTitle());
                }
            }
        } else if (!title.isBlank() && !category.equals("Όλες οι κατηγορίες") && subcategory.equals("Όλες οι υποκατηγορίες")) {
            for (Product p : products) {
                if (p.getCategory().equals(category) && (p.getTitle().contains(title) ||  p.getTitle().toLowerCase().contains(title.toLowerCase()) || p.getTitle().toUpperCase().contains(title.toUpperCase()))) {
                    searchResults.add(p.getTitle());
                }
            }
        }else if (!title.isBlank() && !category.equals("Όλες οι κατηγορίες") && !subcategory.equals("Όλες οι υποκατηγορίες")){
            for (Product p : products) {
                if (p.getSubcategory().equals(subcategory) && (p.getTitle().contains(title) ||  p.getTitle().toLowerCase().contains(title.toLowerCase()) || p.getTitle().toUpperCase().contains(title.toUpperCase()))) {
                    searchResults.add(p.getTitle());
                }
            }
        }

        if (searchResults.isEmpty()){
            return null;
        }else {
            String[] sR = new String[searchResults.size()];
            sR = searchResults.toArray(new String[0]);
            return sR;
        }
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public static String[] getCategories(){
        int counter=0;
        for (int i=0; i<cat.length;i++){
            if (cat[i][0]!=null){
                counter++;
            }
        }
        categories=new String[counter+1];
        categories[0]="Όλες οι κατηγορίες";
        for (int i=0;i<counter;i++){
            categories[i+1]=cat[i][0];
        }
        return categories;
    }

    public static String[] getSubcategories(String category){
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
        subcategories=new String[counter+1];
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

    public void setProducts(){
        try {
            products= Utilities.productsLoader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}