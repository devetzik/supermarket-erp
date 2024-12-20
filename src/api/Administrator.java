package api;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Administrator extends User implements Serializable {
    private Utilities util=new Utilities();
    private ArrayList<Product> products=util.productsLoader();
    private ArrayList<Order> orderHistory=util.orderHistoryLoader();
    private String [][] cat= util.catLoader();


    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public int addProduct(String title, String description, String category, String subcategory, double price, double qty) throws IOException {
        if (title.isBlank() || description.isBlank() || price==0){
            return 1;
        }

        Product product=new Product(title,description,category,subcategory,price,qty);
        util.productsWriter(product);

        return 0;
    }


    public void editProduct(Product product,String title, String description, String category, String subcategory, double price, double qty) throws IOException {
        Product newP= new Product(title, description, category, subcategory, price, qty);

        util.productsWriter(newP);
        util.productsRemover(product);
    }

    public String[] noInvProducts(){
        int counter=0;
        for (Product i : products){
            if (i.getQty()==0){
                counter++;
            }
        }
        if (counter>0) {
            int x=-1;
            String[] noInv = new String[counter];
            for (Product i: products){
                if (i.getQty()==0){
                    x++;
                    noInv[x]=i.getTitle();
                }
            }
            return noInv;
        }
        return null;
    }

    public HashMap <String,Integer> mostSold() throws IOException {
        HashMap<String, Integer> sales=new HashMap<>();
        HashMap<String, Integer> tmp=new HashMap<>();
        for (Order i : orderHistory) {
            for (int j = 0; j < i.getPr().length; j++) {
                if (tmp.containsKey(i.getPr()[j][0])) {
                    tmp.put(i.getPr()[j][0], tmp.get(i.getPr()[j][0])+1);
                } else {
                    tmp.put(i.getPr()[j][0], 1);
                }
            }
        }

        for (String i : tmp.keySet()){
            if (i!=null){
                sales.put(i,tmp.get(i));
            }
        }

        return sortByValue(sales,false);
    }

    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortMap, final boolean order) {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
