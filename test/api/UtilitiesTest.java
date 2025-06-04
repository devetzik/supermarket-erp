package api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void addCustomerBlankUsernameTest() {
        String username=" ";
        String password="password";
        String fName="fName";
        String lName="lName";
        assertEquals(3,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerBlankPasswordTest() {
        String username="username";
        String password=" ";
        String fName="fName";
        String lName="lName";
        assertEquals(3,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerBlankFNameTest() {
        String username="username";
        String password="password";
        String fName=" ";
        String lName="lName";
        assertEquals(3,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerBlankLNameTest() {
        String username="username";
        String password="password";
        String fName="fName";
        String lName=" ";
        assertEquals(3,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerUsernameAlreadyExistsTest() {
        String username="user1";
        String password="password";
        String fName="fName";
        String lName="lName";
        assertEquals(1,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerFirstNameContainsNumberTest() {
        String username="username";
        String password="password";
        String fName="fName12";
        String lName="lName";
        assertEquals(4,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerLastNameContainsNumberTest() {
        String username="username";
        String password="password";
        String fName="fName";
        String lName="lName12";
        assertEquals(4,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void addCustomerCorrectTest() {
        String username="username";
        String password="password";
        String fName="fName";
        String lName="lName";
        assertEquals(0,Utilities.checkAddCustomer(username,password,fName,lName));
    }

    @Test
    void loginCheckBlankUsernameTest() {
        String username="";
        String password="password";
        assertEquals(3,Utilities.loginCheck(username,password));
    }

    @Test
    void loginCheckBlankPasswordTest() {
        String username="username";
        String password="";
        assertEquals(3,Utilities.loginCheck(username,password));
    }

    @Test
    void loginCheckWrongPasswordTest() {
        String username="user1";
        String password="password2";
        assertEquals(1,Utilities.loginCheck(username,password));
    }


    @Test
    void loginCheckUserNotFoundTest() {
        String username="testUsername";
        String password="testPassword";
        assertEquals(2,Utilities.loginCheck(username,password));
    }

    @Test
    void loginCheckSuccessfulTest() {
        String username="user1";
        String password="password1";
        assertEquals(0,Utilities.loginCheck(username,password));
    }

    @Test
    void adminLoaderTest() {
        ArrayList<Administrator> admins= Utilities.adminLoader();
        assertEquals(2,admins.size());
        assertEquals("admin1",admins.get(0).getUsername());
        assertEquals("password1",admins.get(0).getPassword());
        assertEquals("admin2",admins.get(1).getUsername());
        assertEquals("password2",admins.get(1).getPassword());
    }


    @Test
    void custLoaderTest() {
        ArrayList<Customer> customers=Utilities.custLoader();
        assertEquals("user1",customers.get(0).getUsername());
        assertEquals("password1",customers.get(0).getPassword());
        assertEquals("user2",customers.get(1).getUsername());
        assertEquals("password2",customers.get(1).getPassword());
    }

    @Test
    void productsLoaderTest() {
        ArrayList<Product> products=Utilities.productsLoader();
        assertEquals(38,products.size());
        assertEquals("Πορτοκάλια 1kg",products.get(0).getTitle());
    }
}
