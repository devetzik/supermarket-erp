import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Utilities util = new Utilities();
        ArrayList<Product> p = new ArrayList<>();
        p=util.productsLoader();
        for (Product i : p){
            System.out.println(i.getTitle());
        }
        System.out.println(p.size());
        util.productsRemover(p.get(1));
        p=util.productsLoader();
        for (Product i : p)
            System.out.println(i.getTitle());
        System.out.println(p.size());



        /*
        User currnentUser = null;

        Scanner scanner = new Scanner(System.in);

        while (currnentUser == null) {

            System.out.println("Είσοδος χρήστη (1) ή Εγγραφή νέου χρήστη(2)");
            int x = scanner.nextInt();

            if (x == 1) {
                //LOGIN
                currnentUser = util.login();
            } else if (x == 2) {
                //ADD CUSTOMER
                util.addCustomer();
            }


            //USER IS ADMIN

            if (currnentUser instanceof Administrator) {
                Administrator admin = (Administrator) currnentUser;
                System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΑναζήτηση προϊόντος (2)\nΣτατιστικά προϊόντων (3)");
                x = scanner.nextInt();
                if (x == 1) {
                    admin.addProduct();
                } else if (x == 2) {
                    admin.productSearch();
                } else if (x == 3) {
                    admin.adminStats();
                }
            }


            //USER IS CUSTOMER

            if (currnentUser instanceof Customer) {
                Customer customer=(Customer) currnentUser;
                System.out.println("Επιλέξτε λειτουργία:\nΠροβολή Ιστορικού Παραγγελιών (1)");
                x= scanner.nextInt();
                if (x==1){
                    customer.viewOrderHistory(customer);
                }

            }




        /*

        LOADER TESTS
        for (int i=0; i< admins.size(); i++){
            System.out.println(admins.get(i).getUsername()+"\n"+admins.get(i).getPassword());
        }
        for (int i=0; i<customers.size();i++) {
            System.out.println(customers.get(i).getUsername() + "\n" + customers.get(i).getPassword());
        }
        for (Product i : products){
            System.out.println(i.getDetails()+"\n");
        }

        for (int i=0;i<cat.length;i++) {
            for (int j = 0; j < cat[i].length; j++) {
                if (cat[i][j] != null) {
                    if (j == 0) {
                        System.out.println("\n\n\n");
                    }
                    System.out.println(cat[i][j]);
                    if (j == 0) {
                        System.out.println("\n");
                    }
                }
            }
        }
         */
    }
    }
