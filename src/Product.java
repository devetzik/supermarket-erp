import java.util.ArrayList;

public class Product {

    String title, description, category;
    double price, qty;

    public ArrayList<Product> products;


    public Product(String title, String description, String category, double price, double qty){
        this.title=title;
        this.description=description;
        this.category=category;
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

    public double getPrice(){
        return price;
    }

    public double getQty(){
        return qty;
    }


    public
}
