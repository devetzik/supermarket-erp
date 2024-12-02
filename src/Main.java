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

        ArrayList<Order> orderHistory = new ArrayList<>();
        HashMap<Product, Integer> sales = new HashMap<>();

        User currnentUser = null;
        Utilities util = new Utilities();

        ArrayList<Administrator> admins = new ArrayList<>();
        admins = util.adminLoader();
        ArrayList<Customer> customers = new ArrayList<>();
        customers=util.custLoader();
        ArrayList<Product> products = new ArrayList<>();
        String[][] cat = new String[30][10];

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

            if (admins.contains(currnentUser)) {
                Administrator admin = (Administrator) currnentUser;
                System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΑναζήτηση προϊόντος (2)\nΣτατιστικά προϊόντων (3)");
                x = scanner.nextInt();
                if (x == 1) {
                    admin.addProduct();
                } else if (x == 2) {
                    admin.productSearch(products);
                } else if (x == 3) {
                    admin.adminStats(products, orderHistory, sales);
                } else System.out.println("Επιλέξτε ένα από τα παραπάνω (1-2-3)");
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
}
