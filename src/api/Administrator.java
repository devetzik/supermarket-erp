package api;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Administrator extends User implements Serializable {
    private static ArrayList<Product> products;

    static {
        try {
            products = Utilities.productsLoader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ArrayList<Order> orderHistory;

    static {
        try {
            orderHistory = Utilities.orderHistoryLoader();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Κατασκευαστής για το αντικείμενο Administrator

    public Administrator(String username, String password) throws IOException, ClassNotFoundException {
        super(username,password);
    }


    // Μέθοδος για την προσθήκη νέου προϊόντος στο σύστημα

    /**
     *
     * @param title
     * @param description
     * @param price
     * @return
     */
    public int CheckAddProduct(String title, String description, double price){
        if (title.isBlank() || description.isBlank() || price==0){
            return 1;
        }

        return 0;
    }

    /**
     *
     * @param title
     * @param description
     * @param category
     * @param subcategory
     * @param price
     * @param qty
     */
    public static void addProduct(String title, String description, String category, String subcategory, double price, double qty){
        Product product=new Product(title,description,category,subcategory,price,qty);
        try {
            Utilities.productsWriter(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public HashMap <String,Integer> mostSold() {
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

    /**
     *
     * @param unsortMap
     * @param order
     * @return
     */
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

    public static void setProducts(){
        try {
            products= Utilities.productsLoader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
