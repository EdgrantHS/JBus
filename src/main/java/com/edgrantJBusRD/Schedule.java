package com.edgrantJBusRD;
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

    public void bookSeat(String seat){
        this.seatAvailability.put(seat, false);
    }

    public void bookSeat(List <String> seat){
        for (String satuSeat: seat) {
            this.seatAvailability.put(satuSeat, false);
        }
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
    public boolean isSeatAvailable(String seat) {
        //System.out.println("MASUK SEAT AVAILABLE : " + seat);
        return seatAvailability.getOrDefault(seat, false);
    }
    public boolean isSeatAvailable(List <String> seat) {
        for (String satuSeat: seat) {
            if(seatAvailability.getOrDefault(satuSeat, false)) return true;
        }
        return false;
    }

    private void initializeSeatAvailability(int numberOfSeats){
        LinkedHashMap<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++){
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("RD" + sn, true);
        }

        this.seatAvailability = seatAvailability;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss.s");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        int seatCount = 0;
        for (boolean var : seatAvailability.values()){
            if (!var) seatCount++;
        }

        return ("Schedule\t: " + formattedDepartureSchedule + "\nOccupied\t: " + seatCount + "/" + this.seatAvailability.size()); //!masih salah
    }
}