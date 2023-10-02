package edgrantJBusRD;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;

public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        this.initializeSeatAvailability(numberOfSeats);
    }

    /*private void initializeSeatAvailability(int numberOfSeats){
        LinkedHashMap<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            seatAvailability.put("RD" + seatNumber, true);
        }

        this.seatAvailability = seatAvailability;
    }*/
    
    public boolean isSeatAvailable(String seat) {
        System.out.println("MASUK SEAT AVAILABLE : " + seat);
        return seatAvailability.getOrDefault(seat, false);
    }

    public void bookSeat(String seat){
        this.seatAvailability.put(seat, false);
    }
    
    public void initializeSeatAvailability(int numberOfSeats){
        LinkedHashMap<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();
        
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;//ohhh ini untuk kalau kurang dari 10: 00,01,02..09
            seatAvailability.put("RD" + sn, true);
        }

        this.seatAvailability = seatAvailability;
    }
    
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);

        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }
}
