package com.ifmo.wst.client;


import com.ifmo.wst.generated.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/StationService?wsdl");
        StationService stationService = new StationService(url);
        List<Station> stations = stationService.getStationWebServicePort().getStations();
        for (Station station : stations) {
            System.out.println("name: " + station.getName() +
                    ", deepness: " + station.getDeepness() + ", line: " + station.getLine());
        }
        System.out.println("Total stations: " + stations.size());
    }
}