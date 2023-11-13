package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.*;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/bus")
public class BusController implements BasicGetController<Bus>{

    public static @JsonAutowired(
            value = Bus.class,
            filepath = "src//main//java//com//edgrantJBusRD//json//bus.json"
    ) JsonTable<Bus> busTable;

    @PostMapping("/create")
    BaseResponse<Bus> create
            (
                    @RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int capacity,
                    @RequestParam List<Facility> facilities,
                    @RequestParam BusType busType,
                    @RequestParam int price,
                    @RequestParam int stationDepartureId,
                    @RequestParam int stationArrivalId
            ) {

////         Validate parameters
//        if (name.isBlank() || capacity == 0 || busType == null  ) {
//            return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
//        }
//        Check jika account ada
        if (!(Algorithm.<Account>exists(AccountController.accountTable, e -> e.id == accountId))) {
//            System.out.println(AccountController.accountTable + "tes");
            return new BaseResponse<>(false, "account tidak ditemukan", null);
        }
//        Check jika account renter
        else {
            Account tempAccount = Algorithm.<Account>find(AccountController.accountTable, e -> e.id == accountId);
            if (tempAccount.company == null) {
                return new BaseResponse<>(false, "account bukan renter", null);
            }
        }
//        if (Algorithm.<Account>exists(AccountController.accountTable, e -> e.id == accountId)) {
//            return new BaseResponse<>(false, "account tidak ditemukan", null);
//        }
//        Mencari station id departure
        Station departStation;
        if (Algorithm.<Station>exists(StationController.stationTable, e -> e.id == stationDepartureId)) {
            departStation = Algorithm.<Station>find(StationController.stationTable, e -> e.id == stationDepartureId);
        } else {
            return new BaseResponse<>(false, "departure station tidak ditemukan", null);
        }

//        Mencari station id arival
        Station arrivalStation;
        if (Algorithm.<Station>exists(StationController.stationTable, e -> e.id == stationArrivalId)) {
            arrivalStation = Algorithm.<Station>find(StationController.stationTable, e -> e.id == stationArrivalId);
        } else {
            return new BaseResponse<>(false, "arrival station tidak ditemukan", null);
        }

        Bus newBus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, departStation, arrivalStation);
        busTable.add(newBus);
        return new BaseResponse<>(true, "Berhasil register", newBus);
    }

    @PostMapping("/addSchedule")
    BaseResponse<Bus> addSchedule
            (
                    @RequestParam int busId,
                    @RequestParam String time
            )
    {
        try{
            Bus tempBus = Algorithm.<Bus>find(busTable, e -> e.id == busId);
            tempBus.schedules.add(new Schedule(Timestamp.valueOf(time), tempBus.capacity));
            return new BaseResponse<>(true, "berhasil ditambah schedule", tempBus);
        }
        catch (Exception exception){
            return new BaseResponse<>(false, "tidak berhasil ditambah schedule", null);
        }
    }

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

}
