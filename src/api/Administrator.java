package api;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrator extends User implements Serializable {
    private Utilities util=new Utilities();
    private ArrayList<Product> products=util.productsLoader();
    private ArrayList<Order> orderHistory=util.orderHistoryLoader();
    private HashMap<String, Integer> sales;
    private String [][] cat= util.catLoader();


    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public int addProduct(String title, String description, String category, String subcategory, double price, double qty) throws IOException {

        if (title.isBlank() || description.isBlank() || price==0){
            return 1;
        }


        Product product=new Product(title,description,category,subcategory,price,qty);
        util.productsWriter(product);

        return 0;
    }


    public void editProduct(Product product,String title, String description, String category, String subcategory, double price, double qty) throws IOException {
        Product newP= new Product(title, description, category, subcategory, price, qty);

        util.productsWriter(newP);
        util.productsRemover(product);
    }




    public String[] noInvProducts(){
        int counter=0;
        for (Product i : products){
            if (i.getQty()==0){
                counter++;
            }
        }
        if (counter>0) {
            int x=-1;
            String[] noInv = new String[counter];
            for (Product i: products){
                if (i.getQty()==0){
                    x++;
                    noInv[x]=i.getTitle();
                }
            }
            return noInv;
        }
        return null;
    }

    public void adminStats() throws IOException {
        if (orderHistory.isEmpty()){
            System.out.println("Δεν υπάρχουν δεδομένα");
        }else {
            for (Order i : orderHistory) {
                for (int j = 0; j < i.getPr().length; j++) {
                    if (sales.containsKey(i.getPr()[j][0])) {
                        sales.put(i.getPr()[j][0], sales.get(i.getPr()[j][0] + 1));
                    } else {
                        sales.put(i.getPr()[j][0], 1);
                    }
                }
            }
        }
    }
}
