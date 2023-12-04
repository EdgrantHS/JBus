package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.Serializable;

/**
 * The {@code Voucher} class represents a voucher that can be applied to a price.
 * This class extends the {@link Serializable} class to support serialization.
 */
public class Voucher extends Serializable
{
    /**
     * The name of the voucher.
     */
    public String name;

    /**
     * Indicates whether the voucher has been used (true) or not (false).
     * This field is private and is accessed through the {@link #isUsed()} method.
     */
    private boolean used;

    /**
     * The minimum price required for the voucher to be applicable.
     */
    public double minimum;

    /**
     * The discount or rebate amount associated with the voucher.
     */
    public double cut;

    /**
     * The voucher code.
     */
    public int code;

    /**
     * The type of the voucher (REBATE or DISCOUNT).
     */
    public Type type;

    /**
     * Constructs a new Voucher with the specified attributes.
     *
     * @param name     The name of the voucher.
     * @param code     The voucher code.
     * @param type     The type of the voucher (REBATE or DISCOUNT).
     * @param minimum  The minimum price required for the voucher to be applicable.
     * @param cut      The discount or rebate amount associated with the voucher.
     */
    public Voucher(String name, int code, Type type, double minimum, double cut)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * Applies the voucher to the given price and returns the adjusted price.
     *
     * @param price The price to which the voucher is applied.
     * @return The adjusted price after applying the voucher.
     */
    public double apply(Price price)
    {
        double appliedPrice = price.price;

        if (canApply(price)){
            if (this.type == Type.REBATE){
                appliedPrice = price.price - cut;
                if (appliedPrice < 0){
                    return 0;
                }
            }

            if (this.type == Type.DISCOUNT){
                if (cut >= 100){
                    return 0;
                }
                appliedPrice = price.price - (cut/100 * price.price);
            }
        }

        used = true;
        return appliedPrice;
    }

    /**
     * Checks if the voucher has been used.
     *
     * @return {@code true} if the voucher has been used; otherwise, {@code false}.
     */
    public boolean isUsed()
    {
        return this.used;
    }

    /**
     * Checks if the voucher can be applied to the given price.
     *
     * @param price The price to check for voucher applicability.
     * @return {@code true} if the voucher can be applied; otherwise, {@code false}.
     */
    public boolean canApply(Price price)
    {
        return (price.price > this.minimum) && (!isUsed());
    }

    /**
     * Serializes the voucher object.
     *
     * @return The serialized representation of the voucher.
     */
    public Object write(){
        return this;
    }

    /**
     * Deserializes the voucher object from the provided content.
     *
     * @param content The serialized content to read and deserialize.
     * @return {@code true} if deserialization was successful; otherwise, {@code false}.
     */
    public boolean read(String content){
        return false;
    }
}
