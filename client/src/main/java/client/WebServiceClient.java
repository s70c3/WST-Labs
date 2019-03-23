package client;


import com.wst.ifmo.com.Station;
import com.wst.ifmo.com.StationService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {


    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/StationService?wsdl");
        StationService stationService = new StationService(url);


        List<Station> stations = stationService.getStationWebServicePort().filter(
                null, null , null, true, null);

        for (Station station : stations) {
            System.out.println("name: " + station.getName() +
                      ", line: " + station.getLine());
        }
        System.out.println("Total stations: " + stations.size());

        while (true) {
            System.out.println("Найдите интересующую станцию. Параметры поиска: ");
           // System.in.read();
        }
    }






};