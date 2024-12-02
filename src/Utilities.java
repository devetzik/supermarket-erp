import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    public ArrayList<Administrator> adminLoader() throws IOException, ClassNotFoundException {
        ArrayList<Administrator> admins= new ArrayList<>();
        BufferedReader reader= new BufferedReader(new FileReader("admins.txt"));
        String line;

        while ((line=reader.readLine()) != null){
            String [] s= line.split(";");
            admins.add(new Administrator(s[0],s[1]));
        }
        reader.close();
        return admins;
    }

    public ArrayList<Customer> custLoader() throws IOException, ClassNotFoundException {
        ArrayList<Customer> customers= new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader("customers.txt"));
        String line;
        while ((line= reader.readLine())!=null){
            String [] s =line.split(";");
            customers.add(new Customer(s[0],s[1],s[2],s[3]));
        }
        reader.close();
        return customers;
    }

    public ArrayList<Product> productsLoader() throws IOException {
        ArrayList<Product> products=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader("products.txt"));
        String line;
        while ((line=reader.readLine())!=null){
            String [] s=line.split(";");
            products.add(new Product(s[0],s[1],s[2],s[3],Double.parseDouble(s[4]),Integer.parseInt(s[5])));
        }
        reader.close();
        return products;
    }

    public String [][] catLoader() throws IOException {
        String[][] cat = new String[30][10];
        String[] c = new String[30];
        String[] scat = new String[10];
        BufferedReader reader = new BufferedReader(new FileReader("categories_subcategories.txt"));
        int i = 0;
        String line, line2;
        while ((line = reader.readLine()) != null) {
            c = line.split(";");
            line2=c[1];
            scat=line2.split("@");
            cat[i][0]=c[0];
            for (int j=0;j< scat.length;j++){
                cat[i][j+1]=scat[j];
            }
            i++;
        }
        reader.close();
        return cat;}
    }
