package api;

public class Order{
    private String username,datetime;
    private String[][] pr;
    private double total;

    public Order(String username, String[][] pr, String dateTime, double total){
        this.username=username;
        this.pr=pr;
        this.datetime=dateTime;
        this.total=total;
    }


    public double getTotal(){
        return total;
    }

    public String getUsername() {
        return username;
    }

    public String getDatetime() {return datetime;}

    public String[][] getPr(){return pr;}
}