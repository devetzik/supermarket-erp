package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getUnitKgFruitTest() {
        Product product=new Product("title", "description","category","Φρούτα", 10,10);
        assertEquals(" kg  ",product.getUnit());
    }

    @Test
    void getUnitKgVegetableTest() {
        Product product=new Product("title", "description","category","Λαχανικά", 10,10);
        assertEquals(" kg  ",product.getUnit());
    }

    @Test
    void getUnitTmxTest() {
        Product product=new Product("title", "description","category","Κρέατα", 10,10);
        assertEquals(" τμχ.",product.getUnit());
    }


    @Test
    void getQtyTest() {
        Product product=new Product("title", "description","category","Κρέατα", 10,10);
        assertEquals(10,product.getQty());
    }

    @Test
    void getDescriptionTest() {
        Product product=new Product("title", "description","category","Κρέατα", 10,10);
        assertEquals("description",product.getDescription());
    }

    @Test
    void setQtyTest() {
        Product product=new Product("title", "description","category","Κρέατα", 10,10);
        product.setQty(5);
        assertEquals(product.getQty(),5);
    }
}