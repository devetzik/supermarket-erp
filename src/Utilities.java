import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {
    ArrayList<Administrator> admins = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Administrator> adminLoader() throws IOException, ClassNotFoundException {
        ArrayList<Administrator> admins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("admins.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] s = line.split(";");
            admins.add(new Administrator(s[0], s[1]));
        }
        reader.close();
        return admins;
    }

    public ArrayList<Customer> custLoader() throws IOException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] s = line.split(";");
            customers.add(new Customer(s[0], s[1], s[2], s[3]));
        }
        reader.close();
        return customers;
    }

    public ArrayList<Product> productsLoader() throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] s = line.split(";");
            products.add(new Product(s[0], s[1], s[2], s[3], Double.parseDouble(s[4]), Integer.parseInt(s[5])));
        }
        reader.close();
        return products;
    }

    public void productsRemover (Product product) throws IOException {
        ArrayList<Product> products=productsLoader();
        products.remove(product);
        BufferedReader reader=new BufferedReader(new FileReader("products.txt"));
        BufferedWriter writer=new BufferedWriter(new FileWriter("tmp.txt",true));
        String line;
        while ((line= reader.readLine())!=null){
            if (!line.contains(product.title)){
                writer.append(line+"\n");
            }
        }
        reader.close();
        writer.close();
        new FileWriter("products.txt",false).close();
        BufferedReader r= new BufferedReader(new FileReader("tmp.txt"));
        BufferedWriter w=new BufferedWriter(new FileWriter("products.txt"));

        while ((line= r.readLine())!=null){
            w.append(line + "\n");
        }
        r.close();
        w.close();
        new FileWriter("tmp.txt",false).close();
    }

    public void productsWriter(Product product) throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter("products.txt",true));
        writer.append("\n"+product.getTitle()+";"+product.getDescription()+";"+product.getCategory()+";"+product.getSubcategory()+";"+product.getPrice()+";"+product.getQty());
        writer.close();
    }

    public String[][] catLoader() throws IOException {
        String[][] cat = new String[30][10];
        String[] c = new String[30];
        String[] scat = new String[10];
        BufferedReader reader = new BufferedReader(new FileReader("categories_subcategories.txt"));
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
        return cat;
    }

    public ArrayList<Order> orderHistoryLoader() throws IOException, ClassNotFoundException {
        ArrayList<Order> orderHistory=new ArrayList<>();
        ObjectInputStream oos=new ObjectInputStream(new FileInputStream("orderhistory.txt"));
        oos.readObject();
        oos.close();
        return  orderHistory;
    }


    public User login() throws IOException, ClassNotFoundException {
        User currnentUser = null;
        admins = adminLoader();
        customers = custLoader();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Εισάγετε το όνομα χρήστη:");
        String username = scanner.nextLine();
        System.out.println("Εισάγετε τον κωδικό:");
        String password = scanner.nextLine();

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
                    currnentUser = i;
                    break;
                } else {
                    while (!password.equals(i.getPassword())) {
                        System.out.println("Λάθος password, προσπαθήστε ξανά");
                        password = scanner.nextLine();
                        if (password.equals(i.getPassword())) {
                            isAdmin = true;
                            currnentUser = i;
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
                        currnentUser = i;
                        System.out.println("Καλωσήρθατε " + username + "\n");
                        break;
                    } else {
                        while (!password.equals(i.getPassword())) {
                            System.out.println("Λάθος password, προσπαθήστε ξανά");
                            password = scanner.nextLine();
                            if (password.equals(i.getPassword())) {
                                isAdmin = true;
                                currnentUser = i;
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


    public void addCustomer() throws IOException, ClassNotFoundException {
        String username, password, fName, lName;
        admins = adminLoader();
        customers = custLoader();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Εισάγετε το όνομα χρήστη");
        username = scanner.nextLine();
        System.out.println("Εισάγετε τον κωδικό");
        password = scanner.nextLine();
        System.out.println("Εισάγετε το μικρό σας όνομα");
        fName = scanner.nextLine();
        System.out.println("Εισάγετε το επώνυμό σας");
        lName = scanner.nextLine();

        boolean flag = true;

        // Έλεγχος για κενά πεδία κατά το registration

        if (fName.isBlank() || lName.isBlank() || username.isBlank() || password.isBlank()) {
            System.out.println("Συμπληρώστε τα κενά πεδία");
            flag = false;
        }

        // Έλεγχος ύπαρξης του username στη λίστα των customers

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                System.out.println("Το username χρησιμοποιείται");
                flag = false;
            }
        }

        // Έλεγχος ύπαρξης του username στη λίστα των admins

        for (Administrator i : admins) {
            if (username == i.getUsername()) {
                System.out.println("Το username χρησιμοποιείται");
                flag = false;
            }
        }

        // Προσθήκη του νέου πελάτη στη λίστα των customers

        if (flag) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true));
            writer.append("\n" + username + ";" + password + ";" + fName + ";" + lName);
            writer.close();
            System.out.println("Επιτυχής εγγραφή χρήστη");
        }
    }

}


