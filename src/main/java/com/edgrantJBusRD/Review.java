package com.edgrantJBusRD;


import com.edgrantJBusRD.dbjson.Serializable;
/**
 * Represents a review with a date and description.
 */
public class Review extends Serializable {
    /**
     * The date of the review.
     */
    public String date;

    /**
     * The description or content of the review.
     */
    public String desc;

    /**
     * Constructs a Review object with a date and description.
     *
     * @param date The date of the review.
     * @param desc The description or content of the review.
     */
    public Review(String date, String desc) {
        this.date = date;
        this.desc = desc;
    }

    /**
     * Returns a string representation of the review, including its unique ID, date, and description.
     *
     * @return A string representation of the review.
     */
    public String toString() {
        return (super.id + " " + this.date + " " + this.desc);
    }
}
