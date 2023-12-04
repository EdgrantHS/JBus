package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Represents a company or entity that rents buses and has associated contact information.
 */
public class Renter extends Serializable {
    private final static String REGEX_NAME = "^[a-zA-Z0-9_]{4,20}$"; // Regular expression for company name validation
    private final static String REGEX_PHONE = "^[0-9]{9,12}$"; // Regular expression for phone number validation

    /**
     * The address of the renter (optional).
     */
    public String address;

    /**
     * The name of the company or renter.
     */
    public String companyName;

    /**
     * The phone number of the company or renter (optional).
     */
    public String phoneNumber;

    /**
     * Constructs a Renter object with a company name and no phone number or address.
     *
     * @param companyName The name of the company or renter.
     */
    public Renter(String companyName) {
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = "";
    }

    /**
     * Constructs a Renter object with a company name and phone number.
     *
     * @param companyName The name of the company or renter.
     * @param phoneNumber The phone number of the company or renter.
     */
    public Renter(String companyName, String phoneNumber) {
        this.address = "";
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a Renter object with a company name, phone number, and address.
     *
     * @param companyName The name of the company or renter.
     * @param phoneNumber The phone number of the company or renter.
     * @param address     The address of the company or renter.
     */
    public Renter(String companyName, String phoneNumber, String address) {
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Validates the company name and phone number using regular expressions.
     *
     * @return true if both the company name and phone number are valid, false otherwise.
     */
    public boolean validate() {
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher nameMatcher = namePattern.matcher(this.companyName);
        boolean validName = nameMatcher.find();

        Pattern phonePattern = Pattern.compile(REGEX_PHONE);
        Matcher phoneMatcher = phonePattern.matcher(this.phoneNumber);
        boolean validPhone = phoneMatcher.find();

        return validName && validPhone;
    }
}
