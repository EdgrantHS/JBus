package com.edgrantJBusRD;
import com.edgrantJBusRD.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * The Invoice class represents an invoice in the transportation system.
 * It stores information such as the timestamp of the invoice, buyer and renter IDs, bus rating, and payment status.
 */
public class Invoice extends Serializable {

    /**
     * The BusRating enum represents different ratings for a bus.
     * Each enum value corresponds to a specific bus rating.
     */
    public enum BusRating {

        /**
         * Represents no rating.
         */
        NONE,

        /**
         * Represents a neutral rating for a bus.
         */
        NEUTRAL,

        /**
         * Represents a good rating for a bus.
         */
        GOOD,

        /**
         * Represents a bad rating for a bus.
         */
        BAD
    }

    /**
     * The PaymentStatus enum represents different payment statuses for an invoice.
     * Each enum value corresponds to a specific payment status.
     */
    public enum PaymentStatus {

        /**
         * Represents a failed payment status.
         */
        FAILED,

        /**
         * Represents a waiting payment status.
         */
        WAITING,

        /**
         * Represents a successful payment status.
         */
        SUCCESS
    }

    /**
     * The timestamp of the invoice.
     */
    public Timestamp time;

    /**
     * The ID of the buyer associated with the invoice.
     */
    public int buyerId;

    /**
     * The ID of the renter associated with the invoice.
     */
    public int renterId;

    /**
     * The bus rating associated with the invoice.
     */
    public BusRating rating;

    /**
     * The payment status associated with the invoice.
     */
    public PaymentStatus status;

    /**
     * Constructs a new Invoice object with the specified buyer and renter IDs.
     *
     * @param buyerId The ID of the buyer associated with the invoice.
     * @param renterId The ID of the renter associated with the invoice.
     */
    protected Invoice(int buyerId, int renterId)
    {
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructs a new Invoice object with the specified buyer and renter.
     *
     * @param buyer The account of the buyer associated with the invoice.
     * @param renter The renter associated with the invoice.
     */
    public Invoice(Account buyer, Renter renter)
    {
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Returns a string representation of the invoice.
     *
     * @return A string containing invoice details.
     */
    @Override
    public String toString()
    {
        return (super.id + " "  + this.buyerId + " " + this.renterId + " " + this.time.getTime());
    }
}

