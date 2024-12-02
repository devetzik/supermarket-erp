import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable {

    private String username, password, fName, lName;
    User currnentUser;
    String [][] cat;


    // Κατασκευαστής αντικειμένου User

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }





    //Μέθοδος για την προσθήκη νέου πελάτη στο σύστημα

    public ArrayList<Customer> addCustomer(ArrayList<Customer> customers, ArrayList<Administrator>admins) throws IOException, ClassNotFoundException {


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

        // Έλεγχος ύπαρξης του username στη λίστα των customers

        for (Customer i : customers){
            if (username.equals(i.getUsername())){
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
        }
        return customers;
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

    public void productSearch(ArrayList<Product> products) {
        ArrayList<Product> searchResults = new ArrayList<Product>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Αναζήτηση βάσει τίτλου (1)\nΑναζήτηση βάσει κατηγορίας (2)");
        int s=scanner.nextInt();
        if (s==1) {
            System.out.println("Πληκτρολογίστε τον τίτλο του προϊόντος προς αναζήτηση");
            String spare=scanner.nextLine();
            String title= scanner.nextLine();
            for (Product i : products) {
                if (i.getTitle().contains(title)) {
                    searchResults.add(i);
                }
            }
        }else if (s==2){
            System.out.println("Επιλέξτε την κατηγορία του προϊόντος");
            for (int x=0; x< cat.length; x++){
                if (cat[x]!=null) {
                    System.out.println(cat[x][0] + "(" + x + ")");
                }
            }
            int x=scanner.nextInt();
            String category= cat[x][0];
            System.out.println("Επιλέξτε την υποκατηγορία του προϊόντος");
            System.out.println("Καμία υποκατηγορία (100)");
            for (int y=0; y<cat[x].length; y++){
                if (cat[x][y]!=null) {
                    System.out.println(cat[x][y] + "(" + y + ")");
                }
            }
            int y= scanner.nextInt();
            if (y==100){
                for (Product p : products){
                    if (p.getCategory().equals(category)){
                        searchResults.add(p);
                    }
                }
            }else {
                String subcategory=cat[x][y];
                for (Product p : products) {
                    if (p.getSubcategory().equals(subcategory)){
                        searchResults.add(p);
                    }
                }
            }
        }

        if (searchResults != null) {
            System.out.println("Επιλέξτε προϊόν:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(searchResults.get(i).getTitle() + "(" + i + ")");
            }
            int y = scanner.nextInt();
            System.out.println(searchResults.get(y).getDetails());
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