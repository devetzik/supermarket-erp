package api;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    public void newProductEmptyTitleTest() {
        Administrator administrator=new Administrator("admin","admin");
        String title="";
        String description="description";
        double price=10;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void newProductEmptyDesriptionTest() {
        Administrator administrator=new Administrator("admin","admin");
        String title="title";
        String description="";
        double price=10;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void newProductZeroPriceTest() {
        Administrator administrator=new Administrator("admin","admin");
        String title="title";
        String description="description";
        double price=0;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void newProductCorrectTest() {
        Administrator administrator=new Administrator("admin","admin");
        String title="title";
        String description="description";
        double price=10;
        assertEquals(0,administrator.CheckAddProduct(title,description,price));
    }
}