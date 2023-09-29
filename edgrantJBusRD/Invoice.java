package edgrantJBusRD;
import java.util.Calendar;

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
    
    public Calendar time;
    public int buyerId, renterId;
    public BusRating rating;
    public PaymentStatus status;

    protected Invoice(int id, int buyerId, int renterId)
    {
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE; 
        this.status = PaymentStatus.WAITING;
        this.time = Calendar.getInstance(); //!TIME MASIH SALAH
    }
    
    public Invoice(int id, Account buyer, Renter renter)
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
        return ("" + super.id + " "  + this.buyerId + " " + this.renterId + " " + this.time.getTime());
    }
}
