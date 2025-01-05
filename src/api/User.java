package api;

import java.io.*;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private final String username;
    private final String password;
    private static String[][] cat= Utilities.catLoader();
    private static ArrayList<Product> products= Utilities.productsLoader();



    /**
     * Κατασκευαστής: Δημιουργεί έναν χρήστη με τις δεδομένες παραμέτρους.
     *
     * @param username το όνομα χρήστη του χρήστη
     * @param password ο κωδικός του χρήστη
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



    /**
     * Αναζήτα τα προϊόντα που πληρούν τα κριτήρια αναζήτησης, με βάση τον τίτλο, την κατηγορία και την υποκατηγορία που
     * έχει επιλέξει ο χρήστης. Δημιουργεί έναν μονοδιάστατο πίνακα στον οποίο τοποθετεί τα ονόματα των προϊόντων-
     * αποτελεσμάτων και το επιστρέφει.
     *
     * @param title ο τίτλος του προϊόντος που αναζητείται
     * @param category η επιλεγμένη κατηγορία προς αναζήτηση
     * @param subcategory η επιλεγμένη υποκατηγορία προς αναζήτηση
     * @return String[] sR τα ονόματα των προϊόντων που πληρούν τα κριτήρια αναζήτησης
     */
    public static String [] productSearch(String title, String category, String subcategory){
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

    /**
     * Getter για την επιστροφή του username του χρήστη.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Getter για την επιστροφή του password του χρήστη.
     */
    public String getPassword(){
        return password;
    }


    /**
     * Διαβάζει τις κατηγορίες των προϊόντων και δημιουργεί έναν μονοδιάστατο πίνακα String[] στον οποίο
     * τις τοποθετεί και τον επιστρέφει.
     *
     * @return String[] categories, οι κατηγορίες των προϊόντων
     */
    public static String[] getCategories(){
        int counter=0;
        for (int i=0; i<cat.length;i++){
            if (cat[i][0]!=null){
                counter++;
            }
        }
        String[] categories = new String[counter + 1];
        categories[0]="Όλες οι κατηγορίες";
        for (int i=0;i<counter;i++){
            categories[i+1]=cat[i][0];
        }
        return categories;
    }


    /**
     * Διαβάζει τις υποκατηγορίες μιας κατηγορίας που δέχεται ως παράμετρο και δημιοιυργεί έναν μονοδιάστατο πίνακα
     * String[] στον οποίο τις τοποθετεί και τον επιστρέφει.
     *
     * @param category η επιλεγμένη κατηγορία.
     * @return String[] subcategories, οι υποκατηγορίες της επιλεγμένης κατηγορίας.
     */
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
        String[] subcategories = new String[counter + 1];
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



    /**
     * Διαβάζει τα προϊόντα της λίστας products και δημιουργεί έναν μονοδιάστατο πίνακα String[] στον οποίο
     * τοποθετεί τα ονόματά τους και τον επιστρέφει.
     *
     * @return String[] productsNames, τα ονόματα των προϊόντων.
     */
    public static String[] getProductsNames(){
        String[] productsNames=new String[products.size()];
        for (int i=0;i<products.size();i++){
            productsNames[i]=products.get(i).getTitle();
        }
        return productsNames;
    }



    /**
     * Δέχεται ως παράμετρο το όνομα ενός προϊόντος και επιστρέφει το αντικείμενο Product που αντιστοιχεί στο προϊόν.
     *
     * @param productTitle ο τίτλος του προϊόντος
     * @return product, αντικείμενο Product
     */
    public static Product getProduct(String productTitle){
        Product product=null;
        for (Product i:products){
            if (i.getTitle().equals(productTitle)){
                product=i;
            }
        }
        return product;
    }

    /**
     * Setter για τα προϊόντα του supermarket (σε περίπτωση επεξεργασίας/προσθήκης/διαγραφής)
     */
    public static void setProducts(){
        products= Utilities.productsLoader();
    }

    public static ArrayList<Product> getProducts() { return products;}

}