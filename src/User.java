import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User implements Serializable {

    Utilities util=new Utilities();
    private String username, password, fName, lName;
    User currnentUser;
    String [][] cat= util.catLoader();
    ArrayList<Customer> customers=util.custLoader();
    ArrayList <Administrator> admins=util.adminLoader();
    ArrayList<Product> products=util.productsLoader();


    // Κατασκευαστής αντικειμένου User

    public User(String username, String password) throws IOException, ClassNotFoundException {
        this.username=username;
        this.password=password;
    }


    // Μέθοδος για την είσοδο χρήστη στο σύστημα

    public User login(ArrayList<Customer> customers, ArrayList<Administrator> admins) throws IOException, ClassNotFoundException {

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
            BufferedWriter writer=new BufferedWriter(new FileWriter("customers.txt"));
            writer.append(username+";"+password+";"+fName+";"+lName);
            writer.close();
            System.out.println("Επιτυχής εγγραφή χρήστη");
        }
    }


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