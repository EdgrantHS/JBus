package edgrantJBusRD;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    private int busId;
    public String busSeat;
    public Timestamp departureDate; 

    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate)
    {
        super(id, buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    
    public Payment(int id, Account account, Renter renter, int busId, String busSeat, Timestamp departureDate)
    {
        super(id, account, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }

    public String getDepartureInfo(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        
        return (
            "Payment Id: " + super.id + " Buyer Id: " + super.buyerId
            + " Renter Id: " + super.renterId
            + " Departure Date: " + SDFormat.format(this.departureDate.getTime()) 
            + " Bus Seat: " + this.busSeat  
        );
    }
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for (Schedule schedule : bus.schedules) {
            //System.out.println("MASUK isAvailable");       
            //System.out.println("DEBUG  " + schedule.departureSchedule.equals(departureSchedule));
            //System.out.println("DEBUG  " + schedule.departureSchedule);
            //System.out.println("DEBUGMAP  " + schedule.seatAvailability);
            
            if (schedule.departureSchedule.equals(departureSchedule)) {
                //System.out.println("MASUK  " + schedule.departureSchedule);
                return schedule.isSeatAvailable(seat);
            } 
        }
        return false;
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        if (isAvailable(departureSchedule, seat, bus)) {
            for (Schedule schedule : bus.schedules) {
                //System.out.println("MASUK make booking");      
                schedule.bookSeat(seat);
            }
            return true;
        }
        return false;
    }
    
    
    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");

        return SDFormat.format(super.time.getTime());
    }
    
    public int getBusId(){
        return busId;
    }
}
