package client;


import generated.Station;
import generated.StationService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class WebServiceClient {


    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/app/StationService?wsdl");
        StationService stationService = new StationService(url);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Найдите интересующую станцию. Параметры поиска: ");
            System.out.print("Название: ");
            String name = checkNull(in.nextLine());
            System.out.print("\nГород: ");
            String city = checkNull(in.nextLine());
            System.out.print("\nЛиния (название): ");
            String line = checkNull(in.nextLine());
            System.out.print("\nЯвляется конечной (да/нет): ");
            Boolean isend = checkEnd(in.nextLine());

            System.out.print("\nТип: ");
            String type = checkNull(in.nextLine());


            List<Station> stations = stationService.getStationWebServicePort().filter(
                    name, city, line, isend, type);

            if (stations.isEmpty()) { System.out.println("Станций метро не найдено");}
            else {
                for (Station station : stations) {
                    System.out.println("name: " + station.getName() +
                            ", line: " + station.getLine() + " endnees: " + station.isEnd());
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
        return s.equals("да") ? Boolean.TRUE : Boolean.FALSE;
    }


};