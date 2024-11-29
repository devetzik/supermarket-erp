import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        User currnentUser;
        ArrayList<Customer> customers= new ArrayList<>();
        ArrayList<Administrator> admins= new ArrayList<>();
        ArrayList<Product> products= new ArrayList<>();
        ArrayList<Order> orderHistory=new ArrayList<>();

        Scanner scanner= new Scanner(System.in);




        for (int i=0; i<1; i++) {
            System.out.println("Τίτλος");
            String title=scanner.nextLine();
            System.out.println("Περιγραφή");
            String description= scanner.nextLine();
            System.out.println("Κατηγορία");
            String category= scanner.nextLine();
            System.out.println("Υποκατηγορία");
            String subcategory= scanner.nextLine();
            System.out.println("Τιμή");
            double price = scanner.nextDouble();
            System.out.println("qty");
            int qty= scanner.nextInt();
            String spare= scanner.nextLine();

            products.add(new Product(title, description, category, subcategory, price, qty));
        }

        for (Product i: products){
            System.out.println(i.getDetails(i)+"\n");
        }
        System.out.println(products.size());

        ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("products.txt",true));
        writer.writeObject(products);
        writer.close();

        for (Product i: products){
            System.out.println(i.getDetails(i)+"\n");
        }
        System.out.println(products.size());



        //38







        /*
        ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("customers.txt"));
        writer.writeObject(customers);
        writer.close();

        ObjectOutputStream awriter= new ObjectOutputStream(new FileOutputStream("products.txt"));
        awriter.writeObject(products);
        awriter.close();

        ObjectOutputStream bwriter = new ObjectOutputStream(new FileOutputStream("admins.txt"));
        bwriter.writeObject(admins);
        bwriter.close();


        ObjectOutputStream cwriter= new ObjectOutputStream(new FileOutputStream("orderhistory.txt"));
        cwriter.writeObject(orderHistory);
        cwriter.close();
         */




        // LOAD DATA

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

        for (Product i: products){
            System.out.println(i.getDetails(i)+"\n");
        }
        System.out.println(products.size());







/*

        //LOGIN


        System.out.println("Εισάγετε το όνομα χρήστη:\n");
        String username= scanner.nextLine();
        System.out.println("Εισάγετε τον κωδικό:\n");
        String password= scanner.nextLine();

        if (username.isBlank() || password.isBlank()){
            System.out.println("Συμπληρώστε τα πεδία");
        }


        //Έλεγχος για το είδος χρήστη (admin/customer)

        boolean isCust=false, isAdmin=false;

        for (Customer i : customers){
            if (username.equals(i.getUsername())){
                if (password.equals(i.getPassword())){
                    isCust=true;
                    System.out.println("Καλωσήρθατε "+ username);
                    currnentUser=i;
                    break;
                }
                else {
                    System.out.println("Λάθος password");
                }
            }
        }

        if (!isCust) {
            for (Administrator i : admins) {
                if (username.equals(i.getUsername())) {
                    if (password.equals(i.getPassword())) {
                        isAdmin=true;
                        System.out.println("Καλωσήρθατε "+ username);
                        currnentUser = i;
                        break;
                    } else {
                        System.out.println("Λάθος password");
                    }
                }
            }
        }

        if (!isAdmin && !isCust){
            System.out.println("Δεν βρέθηκε χρήστης με το συγκεκριμένο username");
        }

         */











    }
}
