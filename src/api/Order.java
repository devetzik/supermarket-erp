package api;

public class Order{
    String username,datetime;
    String[][] pr;
    double total;

    public Order(String username, String[][] pr, String dateTime, double total){
        this.username=username;
        this.pr=pr;
        this.datetime=dateTime;
        this.total=total;
    }


    public double getTotal(){
        return total;
    }

    public String getDatetime() {return datetime;}
}