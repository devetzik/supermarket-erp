package api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

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
}