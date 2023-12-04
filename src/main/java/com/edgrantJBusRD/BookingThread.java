package com.edgrantJBusRD;

import java.sql.Timestamp;
/**
 * The BookingThread class represents a thread for making a booking in the transportation system.
 * It runs concurrently and attempts to make a booking for a specified bus at a given timestamp.
 */
public class BookingThread extends Thread {

    private final Bus bus;
    private final Timestamp timestamp;

    /**
     * Constructs a new BookingThread with the specified name, bus, and timestamp.
     *
     * @param name      The name of the thread.
     * @param bus       The bus for which the booking is being attempted.
     * @param timestamp The timestamp for the booking.
     */
    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start(); // Start the thread when it is constructed.
    }

    /**
     * The run method represents the logic that the thread executes when started.
     * It attempts to make a booking for the specified bus and timestamp, synchronized to ensure thread safety.
     */
    public void run() {
        System.out.println(
                "Thread " + Thread.currentThread().getName() + " ID : " + Thread.currentThread().getId() + " is running"
        );
        synchronized (this) {
            if (Payment.makeBooking(this.timestamp, "RD01", this.bus)) {
                System.out.println("Thread " + Thread.currentThread().getId() + " Berhasil Melakukan Booking");
            } else {
                System.out.println("Thread " + Thread.currentThread().getId() + " Gagal Melakukan Booking");
            }
        }
    }
}
