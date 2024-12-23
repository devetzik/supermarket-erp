package api;

public class Order{
    private final String username;
    private final String datetime;
    private final String[][] pr;
    private final double total;

    /**
     * Κατασκευαστής: Δημιουργεί μια παραγγελία με τις δεδομένες παραμέτρους
     *
     * @param username το όνομα χρήστη που έκανε την παραγγελία
     * @param pr το καλάθι του χρήστη, περιέχει τα ονόματα των αγορασμένων προϊόντων με τις αντίστοιχες ποσότητες
     * @param dateTime η ημερομηνία καταχώρησης της παραγγελίας
     * @param total το συνολικό κόστος της παραγγελίας
     */
    public Order(String username, String[][] pr, String dateTime, double total){
        this.username=username;
        this.pr=pr;
        this.datetime=dateTime;
        this.total=total;
    }


    /**
     * Getter για το συνολικό κόστος της παραγγελίας
     */
    public double getTotal(){
        return total;
    }

    /**
     * Getter για το username του χρήστη που έκανε την παραγγελία
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter για την ημερομηνία καταχώρησης της παραγγελίας
     */
    public String getDatetime() {return datetime;}

    /**
     * Getter για το καλάθι της παραγγελίας
     */
    public String[][] getPr(){return pr;}
}