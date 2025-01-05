package api;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;



public class Administrator extends User implements Serializable {

    /**
     * Κατασκευαστής: Δημιουργεί έναν διαχειριστή με τις δεδομένες παραμέτρους.
     *
     * @param username το όνομα χρήστη του διαχειριστή
     * @param password το επώνυμο του διαχειριστή
     */
    public Administrator(String username, String password){
        super(username,password);
    }



    /**
     * Ελέγχει τη σωστή εκχώρηση των χαρακτηριστικών ενός νέου προϊόντος στο σύστημα του supermarket.
     *
     * @param title ο τίτλος του προϊόντος
     * @param description η περιγραφή του προϊόντος
     * @param price η τιμή του προϊόντος
     * @return 0 αν έχει γίνει σωστή εκχώρηση, 1 αν κάποιο από τα απαιτούμενα πεδία είναι κενό
     */
    public int CheckAddProduct(String title, String description, double price){
        if (title.isBlank() || description.isBlank() || price==0){
            return 1;
        }

        return 0;
    }



    /**
     * Προσθέτει ένα νέο προϊόν στο σύστημα του supermarket.
     *
     * @param title ο τίτλος του προϊόντος
     * @param description η περιγραφή του προϊόντος
     * @param category η κατηγορία του προϊόντος
     * @param subcategory η υποκατηγορία του προϊόντος
     * @param price η τιμή του προϊόντος
     * @param qty η ποσότητα του διαθέσιμου αποθέματος του προϊόντος
     */
    public static void addProduct(String title, String description, String category, String subcategory, double price, double qty){
        Product product=new Product(title,description,category,subcategory,price,qty);
        Utilities.productsWriter(product);
    }


    /**
     * Ελέγχει αν υπάρχουν προϊόντα με μηδενικό απόθεμα.
     *
     * @return Έναν μονοδιάστατο πίνακα String[] με τους τίτλους των προϊόντων με μηδενικό απόθεμα/
     * null αν δεν υπάρχουν τέτοια προϊόντα
     */
    public String[] noInvProducts(){
        ArrayList<Product> products = Utilities.productsLoader();
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
     * Ελέγχει το ιστορικό παραγγελιών του supermarket και καταγράφει για το κάθε προϊόν, σε πόσες παραγγελίες έχει
     * εμφανιστεί.
     *
     * @return Ένα ταξινομημένο hashmap με τα προϊόντα και τις φορές που έχουν συμπεριληφθεί σε παραγγελίες
     */
    public HashMap <String,Integer> mostSold() {
        ArrayList<Order> orderHistory = Utilities.orderHistoryLoader();
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
     * Ταξινομεί τα περιεχόμενα ενός Hashmap με κριτήριο τις τιμές (values)
     *
     * @param unsortMap το μη-ταξινομημένο Hashmap
     * @param order boolean τιμή για να καθορίσει τη σειρά ταξινόμησης (αύξουσα/φθίνουσα)(true/false)
     * @return το ταξινομημένο Hashmap
     */
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> unsortMap, final boolean order) {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }


    /**
     * Getter για την επιλεγμένη μάσκα
     * @param format το format της μάσκας
     */
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
