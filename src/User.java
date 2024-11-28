import java.io.*;
import java.util.ArrayList;

public abstract class User implements Serializable {

    private String username, password, fName, lName;
    ArrayList<Customer> customers;
    ArrayList<Administrator> admins;
    //ArrayList<Product> products;
    User currnentUser;


    // Κατασκευαστής αντικειμένου User

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }


    // Μέθοδος για την φόρτωση της λίστας πελατών από αρχείο

    public void loadCustomers () throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("customers.txt"));
        customers= (ArrayList<Customer>) reader.readObject();
        reader.close();
    }


    // Μέθοδος για την φόρτωση την λίστας διαχειριστών από αρχείο

    public void loadAdmins() throws IOException, ClassNotFoundException {
        ObjectInputStream reader=new ObjectInputStream(new FileInputStream("admins.txt"));
        admins= (ArrayList<Administrator>) reader.readObject();
        reader.close();
    }


    /*// Μέθοδος για την φόρτωση την λίστας προϊόντων από αρχείο

    public void loadProducts() throws IOException, ClassNotFoundException {
        ObjectInputStream reader=new ObjectInputStream(new FileInputStream("products.txt"));
        products= (ArrayList<Product>) reader.readObject();
        reader.close();
    }
    */


    //Μέθοδος για την προσθήκη νέου πελάτη στο σύστημα

    public void addCustomer(String fName, String lName, String username, String password) throws IOException, ClassNotFoundException {
        boolean flag=true;

        // Έλεγχος για κενά πεδία κατά το registration

        if (fName.isBlank() || lName.isBlank() || username.isBlank() || password.isBlank()){
            System.out.println("Συμπληρώστε τα κενά πεδία");
            flag=false;
        }
        /*if (customers.contains(customer)){
            System.out.println("Ο Πελάτης είναι ήδη εγγεγραμμένος");
           flag=false;
        }*/

        // Έλεγχος ύπαρξης του username στη λίστα των customers

        for (Customer i : customers){
            if (username==i.getUsername()){
                System.out.println("Το username χρησιμοποιείται");
                flag=false;
            }
        }

        // Έλεγχος ύπαρξης του username στη λίστα των admins

        for (Administrator i: admins){
            if (username==i.getUsername()){
                System.out.println("Το username χρησιμοποιείται");
                flag=false;
            }
        }

        // Προσθήκη του νέου πελάτη στη λίστα των customers

        if (flag){
            Customer customer = new Customer(fName, lName, username, password);
            customers.add(customer);

            ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("customers.txt"));
            writer.writeObject(customers);
            writer.close();

            ObjectInputStream reader=new ObjectInputStream(new FileInputStream("admins.txt"));
            admins= (ArrayList<Administrator>) reader.readObject();
            reader.close();
        }
    }


    // Μέθοδος για την είσοδο χρηστών στην εφαρμογή

    public void login(String username, String password){

        //Έλεγχος για τη σωστή συμπλήρωση των πεδίων

        if (username.isBlank() || password.isBlank()){
            System.out.println("Συμπληρώστε τα πεδία");
        }

        //Έλεγχος για το είδος χρήστη

        boolean isCust=false, isAdmin=false;

        for (Customer i : customers){
            if (username==i.getUsername()){
                if (password==i.getPassword()){
                    isCust=true;
                    System.out.println("Καλωσήρθατε");
                    currnentUser=i;
                    break;
                }
                else {
                    System.out.println("Λάθος password");
                }
            }
        }

        for (Administrator i : admins){
            if (username==i.getUsername()){
                if (password==i.getPassword()){
                    isAdmin=true;
                    System.out.println("Καλωσήρθατε");
                    currnentUser=i;
                    break;
                }
                else {
                    System.out.println("Λάθος password");
                }
            }
        }
    }


    // Μέθοδος για την αποσύνδεση χρηστών

    public void logout(){
        username=null;
        password=null;
        fName=null;
        lName=null;
        currnentUser=null;
    }


    // Μέθοδος για την αναζήτηση προϊόντων

    /*public Product productSearch(String title){
        for (Product i : products){
            if (title==i.getTitle()){
                return i;
            }
        }
    }*/

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