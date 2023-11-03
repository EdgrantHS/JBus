package edgrantJBusRD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                    Bus.filterByDeparture(busList, City.JAKARTA, 0, 10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
