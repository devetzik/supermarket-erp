package api;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    String title, description, category, subcategory, unit;
    double price;
    int qty;

    public ArrayList<Product> products;


    public Product(String title, String description, String category, String subcategory, double price, int qty){
        this.title=title;
        this.description=description;
        this.category=category;
        this.subcategory=subcategory;
        this.price=price;
        this.qty=qty;
    }





    // Setters για τα χαρακτηριστικά του προϊόντος

    public void setTitle(String title){
        this.title=title;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public void setSubcategory(String subcategory) { this.subcategory=subcategory;}

    public void setPrice(double price){
        this.price=price;
    }

    public void setQty(int qty){
        this.qty=qty;
    }


    // Getters για τα χαρακτηριστικά του προϊόντος

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getCategory(){
        return category;
    }

    public String getSubcategory(){ return subcategory;}

    public double getPrice(){
        return price;
    }

    public int getQty(){
        return qty;
    }


    public String getDetails(){
        if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")){
            unit="kg";
        }
        else {
            unit=" τεμάχια";
        }
        return ("Τίτλος: "+ title+"\nΠεριγραφή: "+ description+"\nΚατηγορία: "+category+"\nΥποκατηγορία: "+subcategory+"\nΤιμή: "+price+"€\nΠοσότητα: "+qty+unit);
    }
}
