package api;

import org.junit.Test;

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


    @org.junit.jupiter.api.Test
    void noInvProductsTest() {
        Product product1=new Product("title1", "description", "category", "subcategory", 1,10);
        Product product2=new Product("title2", "description", "category", "subcategory", 2,0);
        Product product3=new Product("title3", "description", "category", "subcategory", 3,0);
        Product product4=new Product("title4", "description", "category", "subcategory", 4,10);
        Product product5=new Product("title5", "description", "category", "subcategory", 3,0);
        Product product6=new Product("title6", "description", "category", "subcategory", 4,10);
        String[] noInvTest=new String[3];
        noInvTest[0]=product2.getTitle();
        noInvTest[1]=product3.getTitle();
        noInvTest[2]=product5.getTitle();


    }
}