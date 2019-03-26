package client;


import com.wst.ifmo.com.Station;
import com.wst.ifmo.com.StationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    private static BufferedReader in;
    public static StationService stationService;

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/app/StationService?wsdl");
        stationService = new StationService(url);

        in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в систему информации о станциях метро. Что вы хотите сделать?");
        while (true) {
            System.out.println("Введите команду (1 - найти/2 - обновить/3 - удалить/4 -добавить):");
            int command = 0;
            try {
                command = Integer.parseInt(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (command) {
                case 1:
                    read();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    create();
                    break;
                default:
                    break;
            }
        }


    }

    public static String checkNull(String s) {
        return s.length() == 0 ? null : s;
    }

    public static Boolean checkEnd(String s) {
        if (s.length() == 0) return null;
        return s.equals("да") ? Boolean.TRUE : Boolean.FALSE;
    }

    public static String getColumn(String msg) {
        System.out.print(msg);
        try {
            return checkNull(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean getBooaleanColumn(String msg) {
        System.out.print(msg);
        try {
            return checkEnd(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getIntColumn(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void read() {
        System.out.println("Найдите интересующую станцию. Параметры поиска:");

        String name = getColumn("Название станции: ");
        String city = getColumn("Город: ");
        String line = getColumn("Линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = getColumn("Тип: ");

        List<Station> stations = stationService.getStationWebServicePort().read(
                name, city, line, isend, type);

        if (stations.isEmpty()) {
            System.out.println("Станций метро не найдено");
        } else {
            for (Station station : stations) {
                System.out.println(
                        "Station{" +
                                "id" +station.getId()+'\''+
                                "name='" + station.getName() + '\'' +
                                ", isEnd=" + station.isEnd() +
                                ", city='" + station.getCity() + '\'' +
                                ", line='" + station.getLine() + '\'' +
                                ", station_type='" + station.getStationType() + '\'' +
                                '}');
        }
        System.out.println("Total stations: " + stations.size());
    }

}

    public static int update() {
        System.out.println("Обновите станцию. Параметры поиска: ");

        int id = getIntColumn("ID: ");
        String name = getColumn("Новое название станции: ");
        String city = getColumn("Новый город: ");
        String line = getColumn("Новая линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = checkNull(getColumn("Тип: "));

        int status = stationService.getStationWebServicePort().update(id, name, city, line, isend, type);
        System.out.println(status);
        return status;

    }

    public static int delete() {
        int id = getIntColumn("ID: ");
        int status = stationService.getStationWebServicePort().delete(id);
        System.out.println(status);
        return status;

    }

    public static long create() {
        System.out.println("Добавьте станцию. ");

        String name = getColumn("Название станции: ");
        String city = getColumn("Город: ");
        String line = getColumn("Линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = checkNull(getColumn("Тип: "));

        long status = stationService.getStationWebServicePort().create(name, city, line, isend, type);
        System.out.println(status);
        return status;

    }
}