package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.*;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
/**
 * The BusController class handles HTTP requests related to buses in a transportation system.
 * It provides endpoints for creating buses, adding schedules, and retrieving buses associated with an account.
 */
@RestController
@RequestMapping( "/bus")
public class BusController implements BasicGetController<Bus>{
    /**
     * The JSON table representing the collection of buses.
     */
    public static @JsonAutowired(
            value = Bus.class,
            filepath = "src//main//java//com//edgrantJBusRD//json//bus.json"
    ) JsonTable<Bus> busTable;

    /**
     * Creates a new bus and registers it in the system.
     *
     * @param accountId           The ID of the account associated with the bus.
     * @param name                The name of the bus.
     * @param capacity            The capacity of the bus in terms of passengers.
     * @param facilities          The list of facilities available on the bus.
     * @param busType             The type of the bus (e.g., luxury, standard).
     * @param price               The price of renting the bus.
     * @param stationDepartureId  The ID of the departure station.
     * @param stationArrivalId    The ID of the arrival station.
     * @return A BaseResponse containing information about the success of the operation and the newly created bus.
     */
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

    /**
     * Adds a schedule to an existing bus.
     *
     * @param busId The ID of the bus to which the schedule should be added.
     * @param time  The timestamp for the new schedule.
     * @return A BaseResponse indicating whether the schedule addition was successful.
     */
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

    /**
     * Retrieves a list of buses associated with a specific account.
     *
     * @param accountId The ID of the account for which buses are to be retrieved.
     * @return A List of Bus objects associated with the specified account.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b->b.accountId == accountId);}

    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }


    /**
     * Retrieves a list of all buses.
     *
     * @return A List of all Bus objects.
     */
    @GetMapping("/getAllBus")
    public List<Bus> getAllBus() {
        return getJsonTable();
    }
}
