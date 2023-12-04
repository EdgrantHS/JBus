package com.edgrantJBusRD;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
/**
 * Represents a schedule with a departure timestamp and seat availability status.
 */
public class Schedule {
    /**
     * The departure timestamp of the schedule.
     */
    public Timestamp departureSchedule;

    /**
     * A map representing seat availability, where the key is the seat number (e.g., "RD01") and the value
     * is a boolean indicating seat availability (true for available, false for booked).
     */
    public Map<String, Boolean> seatAvailability;

    /**
     * Constructs a Schedule object with a departure timestamp and initializes seat availability.
     *
     * @param departureSchedule The departure timestamp of the schedule.
     * @param numberOfSeats    The number of seats in the schedule.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        this.initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Books a single seat by marking it as unavailable.
     *
     * @param seat The seat number to be booked.
     */
    public void bookSeat(String seat) {
        this.seatAvailability.put(seat, false);
    }

    /**
     * Books multiple seats by marking them as unavailable.
     *
     * @param seats A list of seat numbers to be booked.
     */
    public void bookSeat(List<String> seats) {
        for (String oneSeat : seats) {
            this.seatAvailability.put(oneSeat, false);
        }
    }

    /**
     * Prints the schedule including the departure timestamp and seat availability.
     * Seats are organized in rows with a maximum of 4 seats per row.
     */
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");

            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    /**
     * Checks if a single seat is available.
     *
     * @param seat The seat number to check.
     * @return True if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat) {
        return seatAvailability.getOrDefault(seat, false);
    }

    /**
     * Checks if any of the provided seats is available.
     *
     * @param seats A list of seat numbers to check.
     * @return True if any of the seats are available, false otherwise.
     */
    public boolean isSeatAvailable(List<String> seats) {
        for (String oneSeat : seats) {
            if (seatAvailability.getOrDefault(oneSeat, false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initializes seat availability for the schedule based on the given number of seats.
     *
     * @param numberOfSeats The total number of seats in the schedule.
     */
    private void initializeSeatAvailability(int numberOfSeats) {
        LinkedHashMap<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            String sn = seatNumber < 10 ? "0" + seatNumber : "" + seatNumber;
            seatAvailability.put("RD" + sn, true);
        }

        this.seatAvailability = seatAvailability;
    }

    /**
     * Returns a string representation of the schedule, including the formatted departure timestamp
     * and the number of occupied seats out of the total seats.
     *
     * @return A string representation of the schedule.
     */
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.s");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());

        int seatCount = 0;
        for (boolean var : seatAvailability.values()) {
            if (!var) seatCount++;
        }

        return ("Schedule\t: " + formattedDepartureSchedule + "\nOccupied\t: " + seatCount + "/" + this.seatAvailability.size());
    }
}
