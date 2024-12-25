package api;

import java.io.*;
import java.util.ArrayList;

public class Utilities {
    private static ArrayList<Administrator> admins = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static User currentUser;

    /**
     * Διαβάζει τους λογαριασμούς τύπου διαχειριστή από αρχείο.
     *
     * @return Ένα Arraylist με τους διαχειριστές (ως αντικείμενα)
     */
    public static ArrayList<Administrator> adminLoader() {
        ArrayList<Administrator> admins = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\admins.txt"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] s = line.split(";");
                admins.add(new Administrator(s[0], s[1]));
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }


    /**
     * Διαβάζει του λογαριασμούς των πελατών από αρχείο.
     *
     * @return Ένα Arraylist με τους πελάτες (ως αντικείμενα)
     */
    public static ArrayList<Customer> custLoader() {
        ArrayList<Customer> customers = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\customers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(";");
                customers.add(new Customer(s[0], s[1], s[2], s[3]));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    /**
     * Διαβάζει τα προϊόντα του supermarket από αρχείο.
     *
     * @return Ένα Arraylist με όλα τα προϊόντα
     */
    public static ArrayList<Product> productsLoader() {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Product> tmp = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\products.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(";");
                Product p = new Product(s[0], s[1], s[2], s[3], Double.parseDouble(s[4]), Double.parseDouble(s[5]));
                tmp.add(p);
            }
            boolean flag;
            for (int i = 0; i < tmp.size(); i++) {
                flag = true;
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getTitle().equals(tmp.get(i).getTitle())) {
                        flag = false;
                    }
                }
                if (flag) {
                    products.add(tmp.get(i));
                }
            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    /**
     * Αποθηκεύει ένα προϊόν στο αρχείο με τα προϊόντα.
     *
     * @param product το προϊόν προς εγγραφή
     */
    public static void productsWriter(Product product) {
        ArrayList<Product> products = new ArrayList<>();
        Product tmp;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\products.txt", true));
            String line;
            boolean flag = true;
            products = productsLoader();
            for (int i = 0; i < products.size(); i++) {
                tmp = products.get(i);
                if (tmp.getTitle().equals(product.getTitle()) && tmp.getCategory().equals(product.getCategory()) && tmp.getSubcategory().equals(product.getSubcategory()) && tmp.getPrice() == product.getPrice() && tmp.getQty() == tmp.getQty()) {
                    flag = false;
                }
            }
            if (flag) {
                writer.append(product.getTitle() + ";" + product.getDescription() + ";" + product.getCategory() + ";" + product.getSubcategory() + ";" + product.getPrice() + ";" + String.format("%.1f", product.getQty()) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Διαγράφει ένα προϊόν από το αρχείο των προϊόντων.
     *
     * @param product το προϊόν προς διαγραφή
     */
    public static void productsRemover(Product product) {
        ArrayList<Product> products = productsLoader();
        products.remove(product);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\products.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\tmp.txt", true));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(product.getTitle())) {
                    writer.append(line + "\n");
                }
            }
            reader.close();
            writer.close();
            new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\products.txt", false).close();
            BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\tmp.txt"));
            BufferedWriter w = new BufferedWriter(new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\products.txt"));

            while ((line = r.readLine()) != null) {
                w.append(line + "\n");
            }
            r.close();
            w.close();
            new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\tmp.txt", false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Διαβάζει από αρχείο, τις κατηγορίες και τις υποκατηγορίες των προϊόντων.
     *
     * @return Ένας δισδιάστατος πίνακας String[][], στον οποίο κάθε σειρά περιέχει στη στήλη 0 το όνομα της κατηγορίας
     * και στις επόμενες στήλες, τις υποκατηγορίες της κατηγορίας αυτής.
     */
    public static String[][] catLoader() {
        String[][] cat = new String[30][10];
        String[] c = new String[30];
        String[] scat = new String[10];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\categories_subcategories.txt"));
            int i = 0;
            String line, line2;
            while ((line = reader.readLine()) != null) {
                c = line.split(";");
                line2 = c[1];
                scat = line2.split("@");
                cat[i][0] = c[0];
                for (int j = 0; j < scat.length; j++) {
                    cat[i][j + 1] = scat[j];
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cat;
    }

    /**
     * Γράφει μια παραγγελία στο αρχείο με το ιστορικό παραγγελιών.
     *
     * @param order η παραγγελία προς εγγραφή
     */
    public static void orderWriter(Order order) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\orderhistory.txt", true));

            if (order.getTotal() != 0) {
                writer.append(order.getUsername() + ";");
                for (int i = 0; i < order.getPr().length; i++) {
                    if (order.getPr()[i][0] != null) {
                        writer.append(order.getPr()[i][0] + "@");
                    }
                }
                writer.append(";");
                for (int i = 0; i < order.getPr().length; i++) {
                    if (order.getPr()[i][1] != null) {
                        writer.append(String.format("%.1f", Double.parseDouble(order.getPr()[i][1])) + "@");
                    }
                }
                writer.append(";" + order.getDatetime() + ";" + String.format("%.2f", order.getTotal()) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Διαβάζει από αρχείο το ιστορικό όλων των παραγγελιών του supermarket
     *
     * @return  Ένα Arraylist με όλες τις παραγγελίες
     */
    public static ArrayList<Order> orderHistoryLoader() {
        ArrayList<Order> orderHistory = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\orderhistory.txt"));

            String line;
            String[] s;

            String[] qty;
            String[] prod;
            while ((line = reader.readLine()) != null) {
                String[][] pr = new String[50][2];
                s = line.split(";");
                prod = s[1].split("@");
                qty = s[2].split("@");
                for (int i = 0; i < prod.length; i++) {
                    pr[i][0] = prod[i];
                    pr[i][1] = qty[i];
                }
                orderHistory.add(new Order(s[0], pr, s[3], Double.parseDouble(s[4])));
            }
            reader.close();
            return orderHistory;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Ελέγχει αν έχει γίνει σωστή εισαγωγή ονόματος χρήστη και κωδικού κατά τη διάρκεια της εισόδου.
     *
     * @param username  το όνομα χρήστη που έχει εισαχθεί
     * @param password  ο κωδικός που έχει εισαχθεί
     * @return  0 αν έχει γίνει σωστή εισαγωγή/ 1 αν το username υπάρχει και το password είναι λάθος/
     * 2 αν το username δεν υπάρχει/ 3 αν τουλάχιστον ένα από τα δύο πεδία (username/password) είναι κενό.
     */
    public static int loginCheck(String username, String password){
        admins = adminLoader();
        customers = custLoader();

        if (username.isBlank() || password.isBlank()) {
            return 3;
        }

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                if (password.equals(i.getPassword())) {
                    currentUser = i;
                    return 0;
                } else {
                    return 1;
                }
            }
        }

        for (Administrator i : admins) {
            if (username.equals(i.getUsername())) {
                if (password.equals(i.getPassword())) {
                    currentUser = i;
                    return 0;
                } else {
                    return 1;
                }
            }

        }
        return 2;
    }


    /**
     * Ελέγχει αν έχει γίνει σωστή εισαγωγή των στοιχείων ενός νέου χρήστη κατά τη διάρκεια της εγγραφής, και αν ναι
     * γράφει τον νέο χρήστη στο αρχείο των χρηστών.
     *
     * @param username  το όνομα χρήστη
     * @param password  ο κωδικός χρήστη
     * @param fName  το όνομα του χρήστη
     * @param lName  το επώνυμο του χρήστη
     * @return  0 αν έχει γίνει σωστή εισαγωγή-εγγραφή του νέου χρήστη/ 1 αν το εισαχθέν username χρησιμοποιείται/
     * 3 αν ένα από τα πεδία είναι κενό/ 4 αν υπάρχει αριθμός στο πεδίο του ονόματος ήτ του επωνύμου
     */
    public static int addCustomer(String username, String password, String fName, String lName) {
        admins = adminLoader();
        customers = custLoader();

        if (fName.isBlank() || lName.isBlank() || username.isBlank() || password.isBlank()) {
            return 3;
        }

        if ((fName.matches(".*\\d.*")) || lName.matches(".*\\d.*")) {
            return 4;
        }

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                return 1;
            }
        }

        for (Administrator i : admins) {
            if (username.equals(i.getUsername())) {
                return 1;
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\Deve\\IdeaProjects\\mymarket-3708\\resources\\customers.txt", true));

            writer.append("\n" + username + ";" + password + ";" + fName + ";" + lName);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }


    /**
     * Εντοπίζει τον χρήστη που έκανε Login στο σύστημα.
     *
     * @param username το όνομα χρήστη
     * @return το αντικείμενο (Customer/Admin) που αντιστοιχεί στο username που εισήχθη
     */
    public static User getCurrentUser(String username){
        for (Customer i: customers){
            if (i.getUsername().equals(username)){
                return i;
            }
        }
        for (Administrator i: admins){
            if (i.getUsername().equals(username)){
                return i;
            }
        }
        return currentUser;
    }
}