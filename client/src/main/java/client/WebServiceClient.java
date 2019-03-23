package client;


import com.wst.ifmo.com.Station;
import com.wst.ifmo.com.StationService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class WebServiceClient {


    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/StationService?wsdl");
        StationService stationService = new StationService(url);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Найдите интересующую станцию. Параметры поиска: ");
            System.out.println("Имя");
            String name = checkNull(in.nextLine());

            System.out.println("Город");
            String city = checkNull(in.nextLine());

            System.out.println("Линия (название)");
            String line = checkNull(in.nextLine());

            System.out.println("Является конечной (да/нет)");
            Boolean isend = checkEnd(in.nextLine());

            System.out.println("Тип");
            String type = checkNull(in.nextLine());


            List<Station> stations = stationService.getStationWebServicePort().filter(
                    name, line, city, isend, type);

            if (stations.isEmpty()) { System.out.println("Станций метро не найдено");}
            else {
                for (Station station : stations) {
                    System.out.println("name: " + station.getName() +
                            ", line: " + station.getLine());
                }
                System.out.println("Total stations: " + stations.size());
            }


        }


    }


    public static  String checkNull (String s) {
        return s.length()==0 ? null : s;
    }
    public static Boolean checkEnd(String s) {
        if (s.length()==0) return null;
        else return s=="да" ? Boolean.TRUE : Boolean.FALSE;
    }


};