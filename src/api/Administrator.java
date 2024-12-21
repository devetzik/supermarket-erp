package api;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Administrator extends User implements Serializable {
    private ArrayList<Product> products= Utilities.productsLoader();
    private ArrayList<Order> orderHistory= Utilities.orderHistoryLoader();

    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    public int CheckAddProduct(String title, String description, String category, String subcategory, double price, double qty) throws IOException {
        if (title.isBlank() || description.isBlank() || price==0){
            return 1;
        }

        return 0;
    }

    public void addProduct(String title, String description, String category, String subcategory, double price, double qty){
        Product product=new Product(title,description,category,subcategory,price,qty);
        try {
            Utilities.productsWriter(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static MaskFormatter getMaskFormatter(String format) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(format);
            mask.setPlaceholderCharacter('0');
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
        return mask;
    }
}
