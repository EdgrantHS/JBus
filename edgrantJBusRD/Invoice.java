package edgrantJBusRD;


/**
 * Write a description of class Invoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    // instance variables - replace the example below with your own
    public String time;
    public int buyerId, renterId;

    /**
     * Constructor for objects of class Invoice
     */
    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
    }

    public String print()
    {
        return ("" + super.id + " "  + this.buyerId + " " + this.renterId + " " + this.time);
    }
}
