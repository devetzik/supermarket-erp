package api;

import org.junit.jupiter.api.Test;

import java.io.*;
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
        orderRemover(customer.getUsername());

    }

    @Test
    void getOrderHistoryTest() {
        ArrayList<Order> tmp=customer.getOrderHistory(customer);
        assertEquals(tmp,customer.getOrderHistory(customer));

    }

    public static void orderRemover(String username) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("resources\\orderhistory.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("resources\\tmp.txt", true));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(username)) {
                    writer.append(line + "\n");
                }
            }
            reader.close();
            writer.close();
            new FileWriter("resources\\orderhistory.txt", false).close();
            BufferedReader r = new BufferedReader(new FileReader("resources\\tmp.txt"));
            BufferedWriter w = new BufferedWriter(new FileWriter("resources\\orderhistory.txt"));

            while ((line = r.readLine()) != null) {
                w.append(line + "\n");
            }
            r.close();
            w.close();
            new FileWriter("resources\\tmp.txt", false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}