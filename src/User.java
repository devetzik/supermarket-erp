import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable {

    private String username, password, fName, lName;
    ArrayList<Customer> customers;
    ArrayList<Administrator> admins;
    ArrayList<Product> products;
    ArrayList<Order> orderHistory;
    User currnentUser;


    // Κατασκευαστής αντικειμένου User

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public void loader() throws IOException, ClassNotFoundException {
        ObjectInputStream custReader = new ObjectInputStream(new FileInputStream("customers.txt"));
        customers= (ArrayList<Customer>) custReader.readObject();
        custReader.close();

        ObjectInputStream adminReader=new ObjectInputStream(new FileInputStream("admins.txt"));
        admins= (ArrayList<Administrator>) adminReader.readObject();
        adminReader.close();

        ObjectInputStream productReader=new ObjectInputStream(new FileInputStream("products.txt"));
        products= (ArrayList<Product>) productReader.readObject();
        productReader.close();

        ObjectInputStream orderHistoryReader= new ObjectInputStream(new FileInputStream("orderhistory.txt"));
        orderHistory = (ArrayList<Order>) orderHistoryReader.readObject();
        orderHistoryReader.close();
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


    // Μέθοδος για την φόρτωση την λίστας προϊόντων από αρχείο

    public void loadProducts() throws IOException, ClassNotFoundException {
        ObjectInputStream reader=new ObjectInputStream(new FileInputStream("products.txt"));
        products= (ArrayList<Product>) reader.readObject();
        reader.close();
    }


    //Μέθοδος για την προσθήκη νέου πελάτη στο σύστημα

    public void addCustomer() throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Εισάγετε το όνομα χρήστη\n");
        username=scanner.nextLine();
        System.out.println("Εισάγετε τον κωδικό\n");
        password=scanner.nextLine();
        System.out.println("Εισάγετε το μικρό σας όνομα");
        fName=scanner.nextLine();
        System.out.println("Εισάγετε το επώνυμό σας");
        lName=scanner.nextLine();

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
            Customer customer = new Customer(username, password, fName, lName);
            customers.add(customer);
            System.out.println("Επιτυχής εγγραφή χρήστη");

            ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("customers.txt",true));
            writer.writeObject(customers);
            writer.close();

            ObjectInputStream custReader = new ObjectInputStream(new FileInputStream("customers.txt"));
            customers= (ArrayList<Customer>) custReader.readObject();
            custReader.close();
        }
    }


    // Μέθοδος για την είσοδο χρηστών στην εφαρμογή

    public User login(ArrayList<Customer> customers, ArrayList<Administrator> admins) {

        currnentUser= new User(null,null) {};

        Scanner scanner=new Scanner(System.in);

        System.out.println("Εισάγετε το όνομα χρήστη:\n");
        String username= scanner.nextLine();
        System.out.println("Εισάγετε τον κωδικό:\n");
        String password= scanner.nextLine();

        //Έλεγχος για τη σωστή συμπλήρωση των πεδίων

        if (username.isBlank() || password.isBlank()) {
            System.out.println("Συμπληρώστε τα πεδία");
        }


        //Έλεγχος για το είδος χρήστη (admin/customer)

        boolean isCust = false, isAdmin = false;

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                if (password.equals(i.getPassword())) {
                    isCust = true;
                    System.out.println("Καλωσήρθατε " + username + "\n");
                    currnentUser=i;
                    break;
                } else {
                    while (!password.equals(i.getPassword())) {
                        System.out.println("Λάθος password, προσπαθήστε ξανά");
                        password = scanner.nextLine();
                        if (password.equals(i.getPassword())) {
                            isAdmin = true;
                            currnentUser=i;
                            System.out.println("Καλωσήρθατε " + username + "\n");
                            break;
                        }
                    }
                }
            }
        }

        if (!isCust) {
            for (Administrator i : admins) {
                if (username.equals(i.getUsername())) {
                    if (password.equals(i.getPassword())) {
                        isAdmin = true;
                        currnentUser=i;
                        System.out.println("Καλωσήρθατε " + username + "\n");
                        break;
                    } else {

                        while (!password.equals(i.getPassword())) {
                            System.out.println("Λάθος password, προσπαθήστε ξανά");
                            password = scanner.nextLine();
                            if (password.equals(i.getPassword())) {
                                isAdmin = true;
                                currnentUser=i;
                                System.out.println("Καλωσήρθατε " + username + "\n");
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (!isAdmin && !isCust) {
            System.out.println("Δεν βρέθηκε χρήστης με το συγκεκριμένο username");
        }
        return currnentUser;
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

    public void productSearchByTitle(String title){
        for (Product i : products){
            if (title==i.getTitle()){
                System.out.println(i.getDetails(i));;
            }
        }
    }


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