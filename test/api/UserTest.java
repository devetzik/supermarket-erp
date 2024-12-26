package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void getCategoriesTest() {
        String[] categories={"Όλες οι κατηγορίες","Φρέσκα τρόφιμα","Κατεψυγμένα τρόφιμα","Προϊόντα ψυγείου", "Αλλαντικά","Αλκοολούχα ποτά",
        "Μη αλκοολούχα ποτά","Καθαριστικά για το σπίτι","Απορρυπαντικά ρούχων", "Καλλυντικά", "Προϊόντα στοματικής υγιεινής",
        "Πάνες","Δημητριακά","Ζυμαρικά","Σνακ","Έλαια","Κονσέρβες","Χαρτικά"};
        assertArrayEquals(categories,User.getCategories());
    }

    @Test
    void getSubcategoriesTest() {
        String[] subcategories={"Όλες οι υποκατηγορίες","Φρούτα","Λαχανικά","Ψάρια","Κρέατα"};
        assertArrayEquals(subcategories,User.getSubcategories("Φρέσκα τρόφιμα"));
    }

    @Test
    void productSearchAllProductsTest() {
        String title="";
        String category="Όλες οι κατηγορίες";
        String subcategory="Όλες οι υποκατηγορίες";

        String[] searchResults=new String[Utilities.productsLoader().size()];
        ArrayList<Product> products=Utilities.productsLoader();
        int i=-1;
        for (Product p:products){
            i++;
            searchResults[i]=p.getTitle();
        }
        assertArrayEquals(searchResults,User.productSearch(title,category,subcategory));
    }

    @Test
    void productSearchNoProductsTest() {
        String title="Κανένα προϊόν ΤΕΣΤ";
        String category="Όλες οι κατηγορίες";
        String subcategory="Όλες οι υποκατηγορίες";

        assertArrayEquals(null,User.productSearch(title,category,subcategory));
    }

    @Test
    void productSearchByCategoryProductsTest() {
        String title="";
        String category="Φρέσκα τρόφιμα";
        String subcategory="Όλες οι υποκατηγορίες";

        String[] searchResults={"Πορτοκάλια 1kg", "Καρότα 1kg", "Κιμάς Μοσχαρίσιος 500g", "Φιλέτο Σολομού 300g"};
        assertArrayEquals(searchResults,User.productSearch(title,category,subcategory));
    }

    @Test
    void productSearchBySubcategoryProductsTest() {
        String title="";
        String category="Φρέσκα τρόφιμα";
        String subcategory="Φρούτα";

        String[] searchResults={"Πορτοκάλια 1kg"};
        assertArrayEquals(searchResults,User.productSearch(title,category,subcategory));
    }

    @Test
    void productSearchByTitleProductsTest() {
        String title="καρ";
        String category="Όλες οι κατηγορίες";
        String subcategory="Όλες οι υποκατηγορίες";

        String[] searchResults={"Καρότα 1kg","Μούσλι με Ξηρούς Καρπούς 500g"};
        assertArrayEquals(searchResults,User.productSearch(title,category,subcategory));
    }

    @Test
    void productSearchByTitleAndCategoryProductsTest() {
        String title="καρ";
        String category="Φρέσκα τρόφιμα";
        String subcategory="Όλες οι υποκατηγορίες";

        String[] searchResults={"Καρότα 1kg"};
        assertArrayEquals(searchResults,User.productSearch(title,category,subcategory));
    }

}