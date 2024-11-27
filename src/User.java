import java.util.ArrayList;

public abstract class User {

    private String username, password, fName, lName;
    ArrayList<Customer> customers;
    ArrayList<Administrator> admins;

    User currnentUser;

    public User(String fName, String lName, String username, String password){
        this.fName=fName;
        this.lName=lName;
        this.username=username;
        this.password=password;
    }


    //Μέθοδος για την προσθήκη νέου πελάτη στο σύστημα

    public void addCustomer(String fName, String lName, String username, String password){
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
                }
                else {
                    System.out.println("Λάθος password");
                }
            }
        }
    }
















    public Product productSearch(String title){
        ArrayList<Product> products;
        for (Product i : products){
            if (title==i.getTitle()){
                return i;
            }
        }
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public User getCurrentUser(String username){
        return currnentUser;
    }


}

