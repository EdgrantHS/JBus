package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.Account;
import com.edgrantJBusRD.Algorithm;
import com.edgrantJBusRD.Renter;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Rest controller for managing account-related operations.
 * This controller handles various endpoints for account operations such as registration, login, and top-up.
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    /**
     * Static JsonTable for managing Account objects.
     * It uses JsonAutowired annotation to specify the source file of the account data.
     */
    public static @JsonAutowired(
            value = Account.class,
            filepath = "src//main//java//com//edgrantJBusRD//json//account.json"
    ) JsonTable<Account> accountTable;

    /**
     * Endpoint to display the account page.
     *
     * @return A string indicating the account page.
     */
    @GetMapping
    String index() { return "account page"; }

    // Other methods are omitted for brevity

    /**
     * Endpoint to register a new account.
     * Registers an account with the given name, email, and password.
     *
     * @param name     The name of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return BaseResponse containing the details of the operation.
     */
    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        BaseResponse<Account> output = new BaseResponse<>(false, "gagal register", null);
        Account newAccount = new Account(name, email, password);
        if (name.isBlank() || newAccount.validate() || Algorithm.<Account>exists(getJsonTable(), e -> e.email.equals(email))){
            return new BaseResponse<>(false, "gagal register", null);
        }

//      jika berhasil register
//      melakukan hash
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte i:
                    bytes) {
                sb.append(Integer.toString((i & 0xff) + 0x100, 16).substring(1));
            }

            newAccount.password = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

 //        Mengembalikan account
        accountTable.add(newAccount);
        return new BaseResponse<>(true, "Berhasil register", newAccount);
    }
    /**
     * Endpoint to register a renter.
     * Allows an existing account to register as a renter with additional details.
     *
     * @param id          The ID of the account.
     * @param companyName The name of the company.
     * @param address     The address of the company.
     * @param phoneNumber The phone number of the company.
     * @return BaseResponse indicating success or failure of the operation.
     */
    @RequestMapping(value = "/{id}/registerRenter", method = RequestMethod.POST)
    BaseResponse<Renter> registerRenter
            (
                    @PathVariable int id,
                    @RequestParam String companyName,
                    @RequestParam String address,
                    @RequestParam String phoneNumber
            )
    {
        // jika ada account
        if (Algorithm.<Account>exists(getJsonTable(), e -> e.id == id)){
            Account tempAccount = Algorithm.<Account>find(getJsonTable(), e -> e.id == id);
//            jika sudah renter
            if (tempAccount.company != null){
                return new BaseResponse<>(false, "sudah renter", null);
            }
//            bukan renter
            else {
                Renter newRenter = new Renter(companyName, phoneNumber, address);
                tempAccount.company = newRenter;
//                accountTable.add(checkAccount);
                return new BaseResponse<>(true, "berhasil buat renter", newRenter);
            }
        }

        //jika tidak ada account
        else{
            return new BaseResponse<>(false, "tidak ada account", null);
        }

    }

    /**
     * Retrieves the JsonTable associated with this controller.
     *
     * @return The JsonTable for Account objects.
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * Endpoint for account login.
     * Authenticates a user based on their email and password.
     *
     * @param email    The email of the user attempting to log in.
     * @param password The password of the user.
     * @return BaseResponse containing the result of the login attempt and account details if successful.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password
    ){
        //hash passwordnya
        String hashPassword = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte i:
                    bytes) {
                sb.append(Integer.toString((i & 0xff) + 0x100, 16).substring(1));
            }

            hashPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        String finalHashPassword = hashPassword;

        if(Algorithm.<Account>exists(getJsonTable(), e -> e.email.equals(email))){
            if (Algorithm.<Account>exists(getJsonTable(), e -> e.password.equals(finalHashPassword))){
                Account newAccount = Algorithm.<Account>find(getJsonTable(), e -> e.email.equals(email) && e.password.equals(finalHashPassword));
                return new BaseResponse<>(true, "Login berhasil", newAccount);
            }
            else{
                return new BaseResponse<>(false, "password salah", null);
            }
        }
        else {
            return new BaseResponse<>(false, "tidak ada email", null);
        }
    }

    /**
     * Endpoint for topping up an account's balance.
     * Increases the balance of an account by a specified amount.
     *
     * @param id     The ID of the account to top up.
     * @param amount The amount to be added to the account's balance.
     * @return BaseResponse indicating the success or failure of the operation and the new balance if successful.
     */
    @RequestMapping(value = "/{id}/topUp", method = RequestMethod.POST)
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double amount
    ){
        if (amount <= 0){
            return new BaseResponse<>(false, "amount tidak valid", null);
        }
        //kalau ada
        if(Algorithm.<Account>exists(getJsonTable(), e -> e.id == id)){
            Account newAccount = Algorithm.<Account>find(getJsonTable(), e -> e.id == id);
            newAccount.balance += amount;
            return new BaseResponse<>(true, "berhasil top up", amount);
        }
        //kalau gak ada
        else{
            return new BaseResponse<>(false, "tidak top up", null);
        }
    }
}
