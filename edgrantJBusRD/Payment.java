package edgrantJBusRD;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public String busSeat;
    public Calendar departureDate; 

    public Payment(int id, int buyerId, int renterId, String time, int busId, String busSeat)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;

        //tambah 2 hari ke departure date
        this.departureDate.add(Calendar.DATE, 2);
    }
    
    
    public Payment(int id, Account account, Renter renter, String time, int busId, String busSeat)
    {
        super(id, account, renter);
        this.busId = busId;
        this.departureDate = Calendar.getInstance();
        this.busSeat = busSeat;

        //tambah 2 hari ke departure date
        this.departureDate.add(Calendar.DATE, 2);
    }

    public String getDepartureInfo(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Formatted Date:' MM/dd/yyyy");
        
        return (
            "" + this.busId + " "   
            + SDFormat.format(this.departureDate.getTime()) + " " 
            + this.busSeat  
        );
    }

    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MM dd, yyyy hh:mm:ss");

        return SDFormat.format(super.time.getTime());
    }
    
    public int getBusId(){
        return busId;
    }
}
