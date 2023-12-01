package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.*;
import com.edgrantJBusRD.Objects.Account;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping( "/payment")
public class PaymentController implements BasicGetController<Payment>{

    public static @JsonAutowired(
            value = Bus.class,
            filepath = "src//main//java//com//edgrantJBusRD//json//payment.json"
    ) JsonTable<Payment> paymentTable;

    @RequestMapping(value = "/makeBooking", method = RequestMethod.POST)
    BaseResponse<Payment> makeBooking
            (
                    @RequestParam int buyerId,
                    @RequestParam int renterId,
                    @RequestParam int busId,
                    @RequestParam List<String> busSeats,
                    @RequestParam BusType busType,
                    @RequestParam String departureDate
            ) {

        // check apakah buyer null
        if (!(Algorithm.<Account>exists(AccountController.accountTable, e -> e.id == buyerId))) {
            return new BaseResponse<>(false, "buyer tidak ada", null);
        }
        // check apakah bus null
        if (!(Algorithm.<Bus>exists(BusController.busTable, e -> e.id == busId))) {
            return new BaseResponse<>(false, "bus tidak ada", null);
        }

        // cek apakah saldo melebihi harga tiket
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, e -> e.id == buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, e -> e.id == busId);
        if (buyer.balance < bus.price.price){
            return new BaseResponse<>(false, "saldo tidak cukup", null);
        }

        // cek apakah saldo melebihi harga tiket
        if (!(Algorithm.<Schedule>exists(bus.schedules, e -> Objects.equals(e.departureSchedule, Timestamp.valueOf(departureDate))))){
            return new BaseResponse<>(false, "jadwal tidak tersedia", null);
        }

        // cek apakah booking sukses
        if (Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, Algorithm.<Bus>find(BusController.busTable, e -> e.id == busId))){
            Payment newPayment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            newPayment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(newPayment);
            return new BaseResponse<>(true, "payment keterima", newPayment);
        }
        else{
            return new BaseResponse<>(false, "payment gagal", null);
        }
    }

    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    BaseResponse<Payment> accept
            (
                    @PathVariable int id
            ) {

        // check apakah payment ada
        if (!(Algorithm.<Payment>exists(paymentTable, e -> e.id == id))) {
            return new BaseResponse<>(false, "payment tidak ada", null);
        }

        //menerima payment
        Payment newPayment = Algorithm.<Payment>find(paymentTable, e -> e.id == id);
        newPayment.status = Invoice.PaymentStatus.SUCCESS;
        return new BaseResponse<>(true, "payment sukses", newPayment);
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    BaseResponse<Payment> cancel
            (
                    @PathVariable int id
            ) {

        // check apakah payment ada
        if (!(Algorithm.<Payment>exists(paymentTable, e -> e.id == id))) {
            return new BaseResponse<>(false, "payment tidak ada", null);
        }

        //menerima payment
        Payment newPayment = Algorithm.<Payment>find(paymentTable, e -> e.id == id);
        newPayment.status = Invoice.PaymentStatus.FAILED;
        return new BaseResponse<>(true, "payment cancel", newPayment);
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

}
