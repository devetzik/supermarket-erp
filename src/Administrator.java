import java.util.ArrayList;

public class Administrator extends User{

    ArrayList<Product> products;

    public Administrator(String fName, String lName, String username, String password){
        super(fName,lName,username,password);
    }

    public void addProduct(String title, String description, String category, double price, double qty){
        if (title.isBlank() || description.isBlank() || category.isBlank() || price<0 || qty<0){
            System.out.println("Λανθασμένη εισαγωγή");
        }
        else {
            Product p = new Product(title, description, category, price, qty);
            products.add(p);
        }
    }

    public void outOfStock(){
        System.out.println("Τα προϊόντα με εξαντλημένο απόθεμα είναι: \n");
        for (Product i : products){
            if (i.getQty()==0){
                System.out.println(i.getTitle() + "\n");
            }
        }
    }


}
