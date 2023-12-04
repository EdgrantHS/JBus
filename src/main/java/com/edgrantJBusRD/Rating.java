package com.edgrantJBusRD;
/**
 * Represents a rating system that calculates the total rating, count of ratings, and average rating.
 */
public class Rating {
    private long count;   // The count of ratings.
    private long total;   // The total sum of ratings.

    /**
     * Constructs a Rating object with an initial count and total of zero.
     */
    public Rating() {
        this.count = 0;
        this.total = 0;
    }

    /**
     * Inserts a new rating into the system and updates the count and total.
     *
     * @param rating The rating to be inserted.
     */
    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }

    /**
     * Returns the total sum of ratings.
     *
     * @return The total sum of ratings.
     */
    public long getTotal() {
        return this.total;
    }

    /**
     * Returns the count of ratings.
     *
     * @return The count of ratings.
     */
    public long getCount() {
        return this.count;
    }

    /**
     * Calculates and returns the average rating.
     *
     * @return The average rating, or 0 if there are no ratings.
     */
    public double getAverage() {
        return count != 0 ? (double) this.total / this.count : 0;
    }

    /**
     * Returns a string representation of the Rating object.
     *
     * @return A string containing the count and total sum of ratings.
     */
    public String toString() {
        return this.count + " " + this.total;
    }
}
