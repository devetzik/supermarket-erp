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

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        User currnentUser=new User(null,null) {};
        ArrayList<Customer> customers= new ArrayList<>();
        ArrayList<Administrator> admins=new ArrayList<>();
        ArrayList<Product> products= new ArrayList<>();
        ArrayList<Order> orderHistory=new ArrayList<>();
        HashMap <Product,Integer>sales= new HashMap<>();
        String [][] cat= new String[50][20];







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

        BufferedReader pr= new BufferedReader(new FileReader("cat.txt"));
        String line;
        int z=0;
        int h=0;
        while ((line= pr.readLine())!=null){
            if (line.equals(" ")){
                z++;
                h=0;
            } else {
                cat[z][h]=line;
                h++;
            }
        }
        pr.close();


        Scanner scanner= new Scanner(System.in);

        while (currnentUser.getUsername()==null) {

            System.out.println("Είσοδος χρήστη (1) ή Εγγραφή νέου χρήστη(2)");
            int x = scanner.nextInt();

            if (x == 1) {
                //LOGIN
                currnentUser = currnentUser.login(customers, admins);
            } else if (x == 2) {
                //ADD CUSTOMER
                customers=currnentUser.addCustomer(customers,admins);

                ObjectOutputStream nc= new ObjectOutputStream(new FileOutputStream("customers.txt"));
                nc.writeObject(customers);
                nc.close();

            } else System.out.println("Επιλέξτε 1 ή 2");
        }



        //USER IS ADMIN

        if (admins.contains(currnentUser)){
            Administrator admin=(Administrator) currnentUser;
            System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΕπεξεργασία προϊόντος (2)\nΑναζήτηση προϊόντος (3)\nΣτατιστικά προϊόντων (4)");
            int x=scanner.nextInt();
            if (x==1){
                products=admin.addProduct(products,cat);

                ObjectOutputStream pw= new ObjectOutputStream(new FileOutputStream("products.txt"));
                pw.writeObject(products);
                pw.close();


            } else if (x==2) {
                products=admin.editProduct(products);
                ObjectOutputStream pw= new ObjectOutputStream(new FileOutputStream("products.txt"));
                pw.writeObject(products);
                pw.close();
            } else if (x==3) {
               admin.productSearch(products);
            } else if (x==4) {
                admin.adminStats(products,orderHistory,sales);
            } else System.out.println("Επιλέξτε ένα από τα παραπάνω (1-2-3-4)");
        }











        //38




       /*for (int i=0; i<1; i++) {
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

           products.add(new Product(title,description,category,subcategory,price,qty));
       }

        */




        //

        /*ObjectOutputStream writer= new ObjectOutputStream(new FileOutputStream("products.txt",true));
        writer.writeObject(products);
        writer.close();

        for (Product i: products){
            System.out.println(i.getDetails(i)+"\n");
        }
        System.out.println(products.size());


         */

        /*

        admins.add(new Administrator("admin1", "password1"));
        admins.add(new Administrator("admin2", "password2"));
        customers.add(new Customer("user1", "password1","fName1","lName1"));
        customers.add(new Customer("user2","password2","fName2", "lName2"));

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







    }
}
