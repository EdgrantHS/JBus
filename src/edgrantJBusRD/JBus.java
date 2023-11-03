package edgrantJBusRD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JBus {
    public static void main(String[] args) {
        try {
            String filepath =
                    "C:\\Users\\Edgrant\\OneDrive - UNIVERSITAS INDONESIA\\Desktop\\UI\\Others\\JBus\\data\\buses.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
            List<Bus> filteredBus =
                    filterByDeparture(busList, City.JAKARTA, 0, 3);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize){
        List<Bus> newBuses = new ArrayList<>();
        newBuses = Algorithm.<Bus>collect(buses, s -> s.departure.city == departure);
        return Algorithm.paginate(newBuses.iterator(), page, pageSize, t -> true);
    }
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
        return Algorithm.<Bus>collect(buses, s -> s.price.price >= min && s.price.price <= max);
    }
    public static Bus filterBusId(List<Bus> buses, int id) {
        return Algorithm.<Bus>find(buses, s -> s.id == id);
    }
    public static List<Bus> filterByDepartureAndArival(List<Bus> buses, City departure, City arrival, int page, int pageSize) {
        List<Bus> newBuses = new ArrayList<>();
        newBuses = Algorithm.<Bus>collect(buses, s -> s.departure.city == departure && s.departure.city == arrival);
        return Algorithm.paginate(newBuses.iterator(), page, pageSize, t -> true);
    }
}
