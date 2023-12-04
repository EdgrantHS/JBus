package com.edgrantJBusRD;

import com.edgrantJBusRD.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Account class represents a user account in the transportation system.
 * It stores information such as user name, email, password, account balance, and company affiliation.
 */
public class Account extends Serializable {

    /**
     * Regular expression for validating email addresses.
     */
    private final static String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    /**
     * Regular expression for validating passwords.
     */
    private final static String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    /**
     * The email address associated with the account.
     */
    public String email;

    /**
     * The user's name.
     */
    public String name;

    /**
     * The user's password.
     */
    public String password;

    /**
     * The company (renter) associated with the account, if applicable.
     */
    public Renter company;

    /**
     * The account balance.
     */
    public double balance;

    /**
     * Creates a new user account with the provided name, email, and password.
     *
     * @param name     The user's name.
     * @param email    The email address associated with the account.
     * @param password The user's password.
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    /**
     * Returns a string representation of the account.
     *
     * @return A string containing account details.
     */
    public String toString() {
        return (
                super.id + " " +
                        this.name + " " +
                        this.email + " " +
                        this.password
        );
    }

    /**
     * Writes the account data to a serialized format.
     *
     * @return An object representing the serialized account data.
     */
    public Object write() {
        return this;
    }

    /**
     * Reads and populates the account data from a serialized format.
     *
     * @param content The content containing serialized account data.
     * @return True if reading and populating the data was successful; otherwise, false.
     */
    public boolean read(String content) {
        return false;
    }

    /**
     * Validates the email and password based on regular expressions.
     *
     * @return True if both email and password are valid; otherwise, false.
     */
    public boolean validate() {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(this.email);
        boolean validEmail = matcher.find();
        pattern = Pattern.compile(REGEX_PASSWORD);
        matcher = pattern.matcher(this.password);
        boolean validPassword = matcher.find();
        return validEmail && validPassword;
    }
}
