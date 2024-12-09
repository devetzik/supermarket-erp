package api;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {
    ArrayList<Administrator> admins = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    User currentUser;

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
            if (!line.contains(product.title) ){
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
        writer.append(product.getTitle()+";"+product.getDescription()+";"+product.getCategory()+";"+product.getSubcategory()+";"+product.getPrice()+";"+product.getQty()+"\n");
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

    public void orderWriter(Order order) throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter("orderhistory.txt",true));
        writer.append(order.username+";");
        for (int i=0;i<order.pr.length;i++){
            if (order.pr[i][0]!=null) {
                writer.append(order.pr[i][0] + "@");
            }
        }
        writer.append(";");
        for (int i=0;i<order.pr.length;i++){
            if (order.pr[i][1]!=null) {
                writer.append(order.pr[i][1] + "@");
            }
        }
        writer.append(";"+order.datetime+";"+(order.getTotal())+"\n");
        writer.close();
    }

    public ArrayList<Order> orderHistoryLoader() throws IOException, ClassNotFoundException {
        ArrayList<Order> orderHistory = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("orderhistory.txt"));
        String line;
        String[] s ;
        String[][] pr = new String[50][50];
        String[] qty;
        String[] prod;
        while ((line = reader.readLine()) != null) {
            s = line.split(";");
            prod = s[1].split("@");
            qty = s[2].split("@");
            for (int i = 0; i < prod.length; i++) {
                pr[i][0] = prod[i];
                pr[i][1] = qty[i];
            }
            orderHistory.add(new Order(s[0],pr,s[3], Double.parseDouble(s[4])));
        }
        return orderHistory;
    }


    public int loginCheck(String username, String password) throws IOException, ClassNotFoundException {
        admins = adminLoader();
        customers = custLoader();

        if (username.isBlank() || password.isBlank()){
            return 3;
        }

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                if (password.equals(i.getPassword())) {
                    currentUser=i;
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


    public int addCustomer(String username, String password, String fName, String lName) throws IOException, ClassNotFoundException {
        admins = adminLoader();
        customers = custLoader();

        boolean flag = true;

        // Έλεγχος για κενά πεδία κατά το registration

        if (fName.isBlank() || lName.isBlank() || username.isBlank() || password.isBlank()) {
            return 3;
        }

        if ((fName.matches(".*\\d.*")) || lName.matches(".*\\d.*")){
            return 4;
        }

        // Έλεγχος ύπαρξης του username στη λίστα των customers

        for (Customer i : customers) {
            if (username.equals(i.getUsername())) {
                return 1;
            }
        }

        // Έλεγχος ύπαρξης του username στη λίστα των admins

        for (Administrator i : admins) {
            if (username.equals(i.getUsername())) {
                return 1;
            }
        }

        // Προσθήκη του νέου πελάτη στη λίστα των customers

        BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true));
        writer.append("\n" + username + ";" + password + ";" + fName + ";" + lName);
        writer.close();

        return 0;
    }

    public User getCurrentUser(){
        return currentUser;
    }


}



