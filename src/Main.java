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
        User currnentUser = null;
        Scanner scanner = new Scanner(System.in);

        while (currnentUser == null) {
            System.out.println("Είσοδος χρήστη (1) / Εγγραφή νέου χρήστη(2) / Τερματισμός προγράμματος (3)");
            int x = scanner.nextInt();
            if (x == 1) {
                //LOGIN
                currnentUser = util.login();
            } else if (x == 2) {
                //ADD CUSTOMER
                util.addCustomer();
            } else if (x==3) {
                System.exit(0);
            }


            //USER IS ADMIN

            while (currnentUser instanceof Administrator) {
                Administrator admin = (Administrator) currnentUser;
                System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΑναζήτηση προϊόντος (2)\nΣτατιστικά προϊόντων (3)\nΑποσύνδεση χρήστη(4)");
                x = scanner.nextInt();
                if (x == 1) {
                    admin.addProduct();
                } else if (x == 2) {
                    admin.productSearch(admin);
                } else if (x == 3) {
                    admin.adminStats();
                } else if (x == 4) {
                    currnentUser = null;
                }
            }


            //USER IS CUSTOMER

            while (currnentUser instanceof Customer) {
                Customer customer = (Customer) currnentUser;
                System.out.println("Επιλέξτε λειτουργία:\nΠροβολή Ιστορικού Παραγγελιών (1)\nΑναζήτηση προϊόντος (2)\nΠροβολή καλαθιού (3)\nΑποσύνδεση χρήστη(4)");
                x = scanner.nextInt();
                if (x == 1) {
                    customer.viewOrderHistory(customer);
                } else if (x == 2) {
                    customer.productSearch(customer);
                } else if (x == 3) {
                    customer.viewShoppingCart();
                } else if (x == 4) {
                    currnentUser = null;
                }

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