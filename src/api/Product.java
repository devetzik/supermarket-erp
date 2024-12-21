package api;

import java.io.Serializable;

public class Product implements Serializable {

    private final String title;
    private final String description;
    private final String category;
    private final String subcategory;
    private final double price;
    private double qty;

    public Product(String title, String description, String category, String subcategory, double price, double qty){
        this.title=title;
        this.description=description;
        this.category=category;
        this.subcategory=subcategory;
        this.price=price;
        this.qty=qty;
    }


    // Setters για τα χαρακτηριστικά του προϊόντος

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
        String unit;
        if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")){
            unit =" kg  ";
        }
        else {
            unit = " τμχ.";
        }
        return unit;
    }
}
