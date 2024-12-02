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

        ArrayList<Order> orderHistory=new ArrayList<>();
        HashMap <Product,Integer>sales= new HashMap<>();

        User currnentUser=null;
        Utilities util=new Utilities();


        ArrayList<Administrator> admins=util.adminLoader();
        ArrayList<Customer> customers=util.custLoader();
        ArrayList<Product> products=util.productsLoader();
        String [][] cat= util.catLoader();

        /*
        for (int i=0; i< admins.size(); i++)
            System.out.println(admins.get(i).getUsername()+"\n"+admins.get(i).getPassword());
        for (int i=0; i<customers.size();i++)
            System.out.println(customers.get(i).getUsername() +"\n"+customers.get(i).getPassword());


        for (int i=0;i<cat.length;i++)
            for (int j=0;j<cat[i].length;j++)
                if (cat[i][j]!=null) {
                    if (j == 0)
                        System.out.println("\n\n\n");
                    System.out.println(cat[i][j]);
                    if (j == 0)
                        System.out.println("\n");
                }

         */



/*
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
            System.out.println("Επιλέξτε λειτουργία:\nΚαταχώρηση νέου προϊόντος (1)\nΑναζήτηση προϊόντος (2)\nΣτατιστικά προϊόντων (3)");
            int x=scanner.nextInt();
            if (x==1){
                products=admin.addProduct(products,cat);

                ObjectOutputStream pw= new ObjectOutputStream(new FileOutputStream("products.txt"));
                pw.writeObject(products);
                pw.close();
            } else if (x==2) {
               admin.productSearch(products);
            } else if (x==3) {
                admin.adminStats(products,orderHistory,sales);
            } else System.out.println("Επιλέξτε ένα από τα παραπάνω (1-2-3)");
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
