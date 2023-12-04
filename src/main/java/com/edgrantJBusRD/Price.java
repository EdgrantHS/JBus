package com.edgrantJBusRD;

/**
 * Represents the price of a product or service, including an optional rebate.
 */
public class Price {
    /**
     * The base price of the product or service.
     */
    public double price;

    /**
     * The rebate amount applied to the price (optional).
     */
    public double rebate;

    /**
     * Constructs a Price object with a specified base price and no rebate.
     *
     * @param price The base price of the product or service.
     */
    public Price(double price) {
        this.price = price;
        this.rebate = 0;
    }

    /**
     * Constructs a Price object with a specified base price and rebate amount.
     *
     * @param price  The base price of the product or service.
     * @param rebate The rebate amount applied to the price.
     */
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
    }

    /**
     * Returns a string representation of the Price object.
     *
     * @return A string containing the base price and rebate (if present).
     */
    public String toString() {
        return this.price + " " + this.rebate;
    }
}
