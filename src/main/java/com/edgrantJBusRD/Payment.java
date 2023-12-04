package com.edgrantJBusRD;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * The Payment class represents a payment and booking for a bus trip in the transportation system.
 * It extends the Invoice class and includes information such as the bus ID, bus seats, and departure date.
 */
public class Payment extends Invoice {

    /**
     * The ID of the bus associated with the payment.
     */
    private final int busId;

    /**
     * The list of bus seats associated with the payment.
     */
    public List<String> busSeats;

    /**
     * The departure date and time associated with the payment.
     */
    public Timestamp departureDate;

    /**
     * Constructs a new Payment object with the specified buyer ID, renter ID, bus ID, bus seats, and departure date.
     *
     * @param buyerId       The ID of the buyer associated with the payment.
     * @param renterId      The ID of the renter associated with the payment.
     * @param busId         The ID of the bus associated with the payment.
     * @param busSeats      The list of bus seats associated with the payment.
     * @param departureDate The departure date and time associated with the payment.
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate) {
        super(buyerId, renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    /**
     * Constructs a new Payment object with the specified buyer, renter, bus ID, bus seats, and departure date.
     *
     * @param account       The account of the buyer associated with the payment.
     * @param renter        The renter associated with the payment.
     * @param busId         The ID of the bus associated with the payment.
     * @param busSeats      The list of bus seats associated with the payment.
     * @param departureDate The departure date and time associated with the payment.
     */
    public Payment(Account account, Renter renter, int busId, List<String> busSeats, Timestamp departureDate) {
        super(account, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeats = busSeats;
    }

    /**
     * Returns a string representation of the departure information associated with the payment.
     *
     * @return A string containing payment and departure information.
     */
    public String getDepartureInfo() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");

        return (
                "Payment Id: " + super.id + " Buyer Id: " + super.buyerId
                        + " Renter Id: " + super.renterId
                        + " Departure Date: " + SDFormat.format(this.departureDate.getTime())
                        + " Bus Seat: " + this.busSeats
        );
    }

    /**
     * Returns the time of the payment as a formatted string.
     *
     * @return A formatted string representing the payment time.
     */
    public String getTime() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");

        return SDFormat.format(super.time.getTime());
    }

    /**
     * Returns the ID of the bus associated with the payment.
     *
     * @return The bus ID.
     */
    public int getBusId() {
        return busId;
    }

    /**
     * Finds and returns the available schedule for booking based on the specified departure schedule and seat.
     * If a matching schedule is found and the seat is available, it returns the schedule; otherwise, it returns null.
     *
     * @param departureSchedule The departure schedule to check for availability.
     * @param seat              The seat to check for availability.
     * @param bus               The bus for which availability is checked.
     * @return The available schedule if found, or null if not available.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
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
        return schedule.isSeatAvailable(seat) ? schedule : null;
    }

    /**
     * Finds and returns the available schedule for booking based on the specified departure schedule and list of seats.
     * If a matching schedule is found and all seats in the list are available, it returns the schedule; otherwise, it returns null.
     *
     * @param departureSchedule The departure schedule to check for availability.
     * @param seats             The list of seats to check for availability.
     * @param bus               The bus for which availability is checked.
     * @return The available schedule if found and all seats are available, or null if not available.
     */
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

    /**
     * Attempts to make a booking for a specified departure schedule and seat on a bus.
     * If the booking is successful (the seat is available), it returns true; otherwise, it returns false.
     *
     * @param departureSchedule The departure schedule for the booking.
     * @param seat              The seat to be booked.
     * @param bus               The bus on which the booking is made.
     * @return True if the booking is successful; otherwise, false.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        Schedule temp = null;
        for (Schedule schedule : bus.schedules) {
            if (schedule.departureSchedule.equals(departureSchedule)) {
                if (schedule.isSeatAvailable(seat)) {
                    temp = schedule;
                    temp.bookSeat(seat);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Attempts to make bookings for a specified departure schedule and a list of seats on a bus.
     * It returns true if all bookings are successful (all seats are available); otherwise, it returns false.
     *
     * @param departureSchedule The departure schedule for the bookings.
     * @param seats             The list of seats to be booked.
     * @param bus               The bus on which the bookings are made.
     * @return True if all bookings are successful; otherwise, false.
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        boolean out = false;
        boolean temp;
        for (String seat : seats) {
            temp = makeBooking(departureSchedule, seat, bus);
            if (!out) out = temp;
        }
        return out;

    }
}
