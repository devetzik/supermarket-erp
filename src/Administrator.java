import java.util.ArrayList;

public class Administrator extends User{

    ArrayList<Product> products;

    public Administrator(String username, String password){
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public void addProduct(String title, String description, String category, String subcategory,double price, double qty){
        if (title.isBlank() || description.isBlank() || category.isBlank() || subcategory.isBlank() || price<0 || qty<0){
            System.out.println("Λανθασμένη εισαγωγή");
        }
        else {
            Product p = new Product(title, description, category, subcategory, price, qty);
            products.add(p);
        }
    }


    // Μέθοδος για την εύρεση προϊόντων με μηδενικό απόθεμα

    public void outOfStock(){
        System.out.println("Τα προϊόντα με εξαντλημένο απόθεμα είναι: \n");
        for (Product i : products){
            if (i.getQty()==0){
                System.out.println(i.getTitle() + "\n");
            }
        }
    }


}
