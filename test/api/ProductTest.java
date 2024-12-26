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
}