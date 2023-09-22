package edgrantJBusRD;


/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    // instance variables - replace the example below with your own
    private int busId;
    public String departureDate, busSeat;

    /**
     * Constructor for objects of class Payment
     */
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat)
    {
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    
    public Payment(int id, Account account, Renter renter, String time, int busId, String departureDate, String busSeat)
    {
        super(id, account, renter, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    public String print()
    {
        // put your code here
        return (
            "" + super.id + " " 
            + super.buyerId + " " 
            + super.renterId + " " 
            + super.time + " "          
            + this.busId + " "   
            + this.departureDate + " " 
            + this.busSeat  
        );
    }
    
    public int getBusId(){
        return busId;
    }
}
