package edgrantJBusRD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.sql.Timestamp.*;

public class JBus {
    public static void main(String[] args) {
        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        bus.schedules.forEach(Schedule::printSchedule);

        try {
            String filepath =
                    "C:\\Users\\Edgrant\\OneDrive - UNIVERSITAS INDONESIA\\Desktop\\UI\\Others\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            Account newAccount = new Account("Edgrant", "edgrant@gmail.com", "Nginx123");
            tableAccount.add(newAccount);
            JsonTable.writeJson(tableAccount, filepath);
            System.out.println(JsonTable.readJson(tableAccount.getClass(),filepath));
//            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
//            List<Bus> filteredBus =
//                    filterByDeparture(busList, City.JAKARTA, 0, 3);
//            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
                Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
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
