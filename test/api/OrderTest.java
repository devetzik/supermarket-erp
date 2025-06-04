package api;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    String username="username";
    String[] products= {"product1", "product2"};
    String[] qtys= {"10", "5"};
    String[][] pr= {products,qtys};
    String date="2024-12-16";
    double total= 15;
    Order order=new Order(username,pr,date,total);;


    @Test
    void getTotalTest() {
        assertEquals(order.getTotal(),total);
    }

    @Test
    void getUsername() {
        assertEquals(order.getUsername(),username);
    }

    @Test
    void getDatetime() {
        assertEquals(order.getDatetime(),date);
    }

    @Test
    void getPr() {
        assertEquals(order.getPr(),pr);
    }
}