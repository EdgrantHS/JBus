package edgrantJBusRD;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Payment extends Invoice
{
    private int busId;
    public String busSeat;
    public Timestamp departureDate;

    public Payment(int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate)
    {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    
    public Payment(Account account, Renter renter, int busId, String busSeat, Timestamp departureDate)
    {
        super(account, renter);
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

public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
     Schedule schedule = null;
     for (Schedule current : bus.schedules) {
         if (current.departureSchedule.equals(departureSchedule)) {
             schedule = current; break;
         }
     }
     if (schedule == null) {
         return null;
     }
     return schedule.isSeatAvailable(seat) ? schedule : null;
}
public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
    // Find the schedule that matches the departure time
    Schedule schedule = null;
    for (Schedule current : bus.schedules) {
        if (current.departureSchedule.equals(departureSchedule)) {
            schedule = current;
            break;
        }
    }
    if (schedule == null) {
        return null;
    }
    boolean allSeatsAvailable = true;
    for (String seat : seats) {
        if (!schedule.isSeatAvailable(seat)) {
            allSeatsAvailable = false;
            break;
        }
    }
    return allSeatsAvailable ? schedule : null;
}


    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        Schedule temp = null;
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)) {
                if(schedule.isSeatAvailable(seat)){
                    temp = schedule;
                    temp.bookSeat(seat);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        boolean out = false;
        boolean temp;
        for (String seat: seats){
            temp = makeBooking(departureSchedule, seat, bus);
            if (!out) out = temp;
        }
        return out;
    }


    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");

        return SDFormat.format(super.time.getTime());
    }
    
    public int getBusId(){
        return busId;
    }
}
