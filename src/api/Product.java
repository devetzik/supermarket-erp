package api;

import java.io.Serializable;

public class Product implements Serializable {

    private String title, description, category, subcategory, unit;
    private double price,qty;


    public Product(String title, String description, String category, String subcategory, double price, double qty){
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

    public void setQty(double qty){
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

    public double getQty(){
        return qty;
    }

    public String getUnit(){
        if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")){
            unit=" kg  ";
        }
        else {
            unit = " τμχ.";
        }
        return unit;
    }


    public String getDetails(){
        if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")){
            unit=" kg";
        }
        else {
            unit=" τεμάχια";
        }
        return ("Τίτλος: "+ title+"\nΠεριγραφή: "+ description+"\nΚατηγορία: "+category+"\nΥποκατηγορία: "+subcategory+"\nΤιμή: "+price+"€\nΠοσότητα: "+qty+unit);
    }
}
