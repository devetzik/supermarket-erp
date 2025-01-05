package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer=new Customer("username","password","fName","lName");
    String fName= customer.getfName();
    String lName= customer.getlName();
    private  HashMap<Product, Double> sC =customer.getShoppingCart();
    private  ArrayList<Product> products= Utilities.productsLoader();
    private  String[][] pr =new String[products.size()][products.size()];
    @Test
    void getTotalTest() {
        Customer customer=new Customer("username","password", "fName", "lName");
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);
        Product product2=new Product("title2", "description", "category", "subcategory", 2,10);
        Product product3=new Product("title3", "description", "category", "subcategory", 3,10);
        Product product4=new Product("title4", "description", "category", "subcategory", 4,10);
        customer.addToShoppingCart(product1,3);
        customer.addToShoppingCart(product2,2);
        customer.addToShoppingCart(product3,5);
        customer.addToShoppingCart(product4,2);
        assertEquals(30,customer.getTotal());
    }

    @Test
    void getOrderHistoryTest() {
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);


        ArrayList<Order> tmp=customer.getOrderHistory(customer);

        assertEquals(tmp,customer.getOrderHistory(customer));

    }


    @Test
    void removeFromCartTest() {
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);
        Product product2=new Product("title2", "description", "category", "subcategory", 2,10);

        HashMap<Product,Double> sC=new HashMap<>();
        sC.put(product1,1.0);
        customer.getShoppingCart().clear();

        customer.addToShoppingCart(product1,1);
        customer.addToShoppingCart(product2,2);

        customer.removeFromCart("title2");

        assertEquals(customer.getShoppingCart(),sC);
    }

    @Test
    void updateCartQtyTest() {
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);

        HashMap<Product,Double> sC=new HashMap<>();
        sC.put(product1,2.0);

        customer.addToShoppingCart(product1,1);
        customer.updateCartQty("title1",2);

        assertEquals(customer.getShoppingCart(),sC);
        customer.removeFromCart("title1");
    }

    @Test
    void confirmOrderTest() {
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);

        customer.addToShoppingCart(product1,1);
        customer.confirmOrder(customer);

        Utilities.productsRemover(product1);
    }
}