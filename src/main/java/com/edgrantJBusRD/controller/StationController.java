package com.edgrantJBusRD.controller;

import com.edgrantJBusRD.City;
import com.edgrantJBusRD.Station;
import com.edgrantJBusRD.dbjson.JsonAutowired;
import com.edgrantJBusRD.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The StationController class handles HTTP requests related to stations in a transportation system.
 * It provides endpoints for creating stations and retrieving a list of all stations.
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {

    /**
     * The JSON table representing the collection of stations.
     */
    public static @JsonAutowired(
            value = Station.class,
            filepath = "src\\main\\java\\com\\edgrantJBusRD\\json\\station.json"
    ) JsonTable<Station> stationTable;

    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }


    /**
     * Creates a new station and adds it to the system.
     *
     * @param stationName The name of the new station.
     * @param city        The city where the station is located.
     * @param address     The address of the station.
     * @return A BaseResponse indicating the success or failure of station creation.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @RequestParam String stationName,
            @RequestParam String city,
            @RequestParam String address
    ) {
        try {
            // Validate parameters
            if (stationName.isBlank() || city.isBlank() || address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            // Validate city as a valid enum value
            City cityEnum = City.valueOf(city.toUpperCase());

            // Create a new station using the provided details
            Station newStation = new Station(stationName, cityEnum, address);

            // Add the new station to the stationTable
            stationTable.add(newStation);

            //Success response message
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum value
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            // Handle other unexpected errors
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }

    /**
     * Retrieves a list of all stations.
     *
     * @return A List of Station objects representing all stations in the system.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() { return getJsonTable();}

}
