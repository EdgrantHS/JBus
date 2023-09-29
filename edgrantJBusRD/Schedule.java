package edgrantJBusRD;
import java.util.Calendar;
import java.util.*;

public class Schedule
{
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Calendar departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        this.initializeSeatAvailability(numberOfSeats);
    }

    private void initializeSeatAvailability(int numberOfSeats){
        LinkedHashMap<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            seatAvailability.put("RD" + seatNumber, true);
        }

        this.seatAvailability = seatAvailability;
    }
}
