package api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {
    private final Administrator administrator=new Administrator("admin","admin");

    @Test
    public void newProductEmptyTitleTest() {
        String title="";
        String description="description";
        double price=10;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void newProductEmptyDesriptionTest() {
        String title="title";
        String description="";
        double price=10;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void newProductZeroPriceTest() {
        String title="title";
        String description="description";
        double price=0;
        assertEquals(1,administrator.CheckAddProduct(title,description,price));
    }


    @Test
    public void newProductCorrectTest() {
        String title="title";
        String description="description";
        double price=10;
        assertEquals(0,administrator.CheckAddProduct(title,description,price));
    }

    @Test
    public void addProductTest() {
        Administrator.addProduct("title","description","category","subcategory",1,10);
        ArrayList<Product> products=Utilities.productsLoader();
        boolean flag=false;
        for (Product i:products){
            if (i.getTitle().equals("title")){
                flag=true;
            }
        }
        assertTrue(flag);
        User.setProducts();
        Utilities.productsRemover(User.getProduct("title"));
    }

    @Test
    public void mostSold() {
        ArrayList<Order> orderHistory = Utilities.orderHistoryLoader();
        HashMap<String, Integer> sales=new HashMap<>();
        HashMap<String, Integer> tmp=new HashMap<>();
        for (Order i : orderHistory) {
            for (int j = 0; j < i.getPr().length; j++) {
                if (tmp.containsKey(i.getPr()[j][0])) {
                    tmp.put(i.getPr()[j][0], tmp.get(i.getPr()[j][0])+1);
                } else {
                    tmp.put(i.getPr()[j][0], 1);
                }
            }
        }

        for (String i : tmp.keySet()){
            if (i!=null){
                sales.put(i,tmp.get(i));
            }
        }

        sales=Administrator.sortByValue(sales,true);
        sales=Administrator.sortByValue(sales,false);

        assertEquals(sales, administrator.mostSold());
    }


    @Test
    public void noNoInvProductsTest() {
        String[] noInv=null;
        ArrayList<Product> products = Utilities.productsLoader();
        int counter=0;
        for (Product i : products){
            if (i.getQty()==0){
                counter++;
            }
        }
        if (counter>0) {
            int x = -1;
            noInv = new String[counter];
            for (Product i : products) {
                if (i.getQty() == 0) {
                    x++;
                    noInv[x] = i.getTitle();
                }
            }

        }
        assertEquals(noInv, administrator.noInvProducts());
    }

    @Test
    public void NoInvProductsTest() {
        Administrator.addProduct("title","description","category","subcategory",1,0);

        String[] noInv=null;
        ArrayList<Product> products = Utilities.productsLoader();
        int counter=0;



        for (Product i : products){
            if (i.getQty()==0){
                counter++;
            }
        }
        if (counter>0) {
            int x = -1;
            noInv = new String[counter];
            for (Product i : products) {
                if (i.getQty() == 0) {
                    x++;
                    noInv[x] = i.getTitle();
                }
            }

        }

        assertArrayEquals(noInv, administrator.noInvProducts());
        Utilities.productsRemover(User.getProduct("title"));
    }
}
