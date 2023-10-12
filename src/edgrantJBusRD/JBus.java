package edgrantJBusRD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JBus {
    public static void main(String[] args) {

        //TP Modul 6

        String filepath = "C:\\Users\\Edgrant\\OneDrive - UNIVERSITAS INDONESIA\\Desktop\\UI\\Others\\JBus\\data\\station.json";
        Gson gson = new Gson();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(bufferedReader, new TypeToken<List<Station>>() {}.getType());
            stationjson.forEach(e -> System.out.println(e.toString()));
            System.out.println();
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

//    public static Bus createBus() {
//        Price price = new Price(750000, 5);
//        Bus bus = new Bus(
//                "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG,
//                new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"),
//                new Station("Halte UI", City.JAKARTA, "Universitas Indonesia")
//        );
//        return bus;
//    }
}