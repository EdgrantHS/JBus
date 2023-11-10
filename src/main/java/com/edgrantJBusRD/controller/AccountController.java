package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.Account;
import com.edgrantJBusRD.Algorithm;
import com.edgrantJBusRD.Renter;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    public static @JsonAutowired(
            value = Account.class,
            filepath = "src//main//java//com//edgrantJBusRD//json//account.json"
    ) JsonTable<Account> accountTable;
    private int id;

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
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

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

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
        if(Algorithm.<Account>exists(getJsonTable(), e -> e.email.equals(email) && e.password.equals(finalHashPassword))){
            Account newAccount = Algorithm.<Account>find(getJsonTable(), e -> e.email.equals(email) && e.password.equals(finalHashPassword));
            return new BaseResponse<>(true, "Login berhasil", newAccount);
        }
        else {
            return new BaseResponse<>(false, "Login gagal", null);
        }
    }

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

//    @RequestMapping(value = "/page", method = RequestMethod.GET)
//    public List<Account> getPage(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "5") int pageSize
//    ){
//        return Algorithm.paginate(getJsonTable(), page, pageSize, e -> true);
//    }
//    @GetMapping("/{id}")
//    public Account getById(@PathVariable int id){
//        return Algorithm.<Account>find(getJsonTable(), e -> e.id == id);
//    }
}
