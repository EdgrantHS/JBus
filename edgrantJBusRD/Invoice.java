package edgrantJBusRD;

public class Invoice extends Serializable
{
    
    public enum BusRating {
        NONE,
        NEUTRAL,
        GOOD,
        BAD;
    }
    
    public enum PaymentStatus {
        FAILED,
        WAITING,
        SUCCESS;
    }
    
    public String time;
    public int buyerId, renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    /**
     * Constructor for objects of class Invoice
     */
    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id);
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String toString()
    {
        return ("" + super.id + " "  + this.buyerId + " " + this.renterId + " " + this.time);
    }
}
