package api;

import java.io.Serializable;

public class Product implements Serializable {

    private final String title;
    private final String description;
    private final String category;
    private final String subcategory;
    private final double price;
    private double qty;



    /**
     * Κατασκευαστής: Δημιουργεί ένα προϊόν με τις δεδομένες παραμέτρους
     *
     * @param title ο τίτλος του προϊόντος
     * @param description η περιγραφή του προϊόντος
     * @param category η κατηγορία του προϊόντος
     * @param subcategory η υποκατηγορία του προϊόντος
     * @param price η τιμή του προϊόντος
     * @param qty το διαθέσιμο απόθεμα του προϊόντος
     */
    public Product(String title, String description, String category, String subcategory, double price, double qty){
        this.title=title;
        this.description=description;
        this.category=category;
        this.subcategory=subcategory;
        this.price=price;
        this.qty=qty;
    }


    /**
     * Setter για το διαθέσιμο απόθεμα του προϊόντος
     * @param qty η νέα διαθέσιμη ποσότητα του προϊόντος
     */
    public void setQty(double qty){
        this.qty=qty;
    }


    /**
     * Getter για τον τίτλο του προϊόντος
     */
    public String getTitle(){
        return title;
    }

    /**
     * Getter για τον τίτλο του προϊόντος
     */
    public String getDescription(){
        return description;
    }

    /**
     * Getter για την κατηγορία του προϊόντος
     */
    public String getCategory(){
        return category;
    }

    /**
     * Getter για την υποκατηγορία του προϊόντος
     */
    public String getSubcategory(){ return subcategory;}

    /**
     * Getter για την τιμή του προϊόντος
     */
    public double getPrice(){
        return price;
    }

    /**
     * Getter για το διαθέσιμο απόθεμα του προϊόντος
     */
    public double getQty(){
        return qty;
    }

    /**
     * Ελέγχει σε ποια υποκατηγορία ανήκει το προϊόν και του αναθέτει την ανάλογη μονάδα μέτρησης κιλά/τεμάχια.
     *
     * @return unit, τη μονάδα μέτρησης του προϊόντος
     */
    public String getUnit(){
        String unit;
        if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")){
            unit =" kg  ";
        }
        else {
            unit = " τμχ.";
        }
        return unit;
    }
}
