import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable {

    Utilities util=new Utilities();
    private String username, password, fName, lName;
    User currnentUser;
    String [][] cat=new String[30][10];
    ArrayList<Customer> customers= new ArrayList<>();
    ArrayList <Administrator> admins= new ArrayList<>();
    ArrayList<Product> products=new ArrayList<>();



    // Κατασκευαστής αντικειμένου User

    public User(String username, String password) throws IOException, ClassNotFoundException {
        this.username=username;
        this.password=password;
    }


    // Μέθοδος για την είσοδο χρήστη στο σύστημα




    //Μέθοδος για την προσθήκη νέου πελάτη στο σύστημα




    // Μέθοδος για την αναζήτηση προϊόντων

    public abstract void productSearch(ArrayList<Product> products);


    // Μέθοδος για την αποσύνδεση χρηστών

    public void logout(){}





    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public User getCurrentUser(){
        return currnentUser;
    }

}