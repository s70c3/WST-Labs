package client;


import com.ifmo.wst.entity.Station;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import sun.misc.BASE64Encoder;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WebServiceClient {

    private static BufferedReader in;
    private static final String URL = "http://localhost:8080/rest/stations";
    private static WebResource resource;
    private static String authStringEnc;
    public static void main(String[] args) throws IOException {

        String name = "user";
        String password = "pass";
        String authString = name + ":" + password;
        authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        Client client = Client.create();
        resource = client.resource(URL);
        ClientResponse resp = resource.accept("application/json")
                .header("Authorization", "Basic " + authStringEnc)
                .get(ClientResponse.class);
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        else {
            System.err.println(resp);
        }

        System.out.println("Добро пожаловать в систему информации о станциях метро. Что вы хотите сделать?");
        in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Введите команду (1 - найти/2 - обновить/3 - удалить/4 -добавить):");
            int command = 0;
            try {
                command = Integer.parseInt(in.readLine());
            } catch (IOException e) {
               System.out.println("Вводите только цифры. ");
            }
            catch (NumberFormatException u) {
                System.out.println("Вводите только цифры. ");
            }

            switch (command) {
                case 1:
                    printList(read(client));
                    break;
                case 2:
                    System.out.println(update());
                    break;
                case 3:
                    System.out.println(delete());
                    break;
                case 4:
                    System.out.println(create());
                    break;
                default:
                    break;
            }
        }

    }


    public static List<Station> read(Client client) {

        System.out.println("Найдите интересующую станцию. Параметры поиска:");

        String name = getColumn("Название станции: ");
        String city = getColumn("Город: ");
        String line = getColumn("Линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = getColumn("Тип: ");

        resource = setParamIfNotNull(resource, "name", name);
        resource = setParamIfNotNull(resource, "city", city);
        resource = setParamIfNotNull(resource, "line", line);
        resource = setParamIfNotNull(resource, "isend", isend);
        resource = setParamIfNotNull(resource, "type", type);

        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Basic " + authStringEnc)
                .get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Station>> stations = new GenericType<List<Station>>() {
        };

        return resource.accept(MediaType.APPLICATION_JSON).get(stations);

    }

    public static int update() {
        System.out.println("Обновите станцию. Параметры поиска: ");

        int id = getIntColumn("ID: ");
        String name = getColumn("Новое название станции: ");
        String city = getColumn("Новый город: ");
        String line = getColumn("Новая линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = getColumn("Тип: ");

        Station station = new Station(name, line, isend, city, type);

        String updateResponse = resource.path(String.valueOf(id))
                .entity(station, MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization", "Basic " + authStringEnc)
                .put(String.class);
        System.out.println(updateResponse);
        return Integer.parseInt(updateResponse);
    }

    public static int delete() {
        int id = getIntColumn("ID: ");
        String body = resource.path(String.valueOf(id)).accept(MediaType.TEXT_PLAIN_TYPE)
                .header("Authorization", "Basic " + authStringEnc)
                .delete(String.class);
        System.out.println(body);
        return Integer.parseInt(body);


    }

    public static long create() {
        System.out.println("Добавьте станцию. ");

        String name = getColumn("Название станции: ");
        String city = getColumn("Город: ");
        String line = getColumn("Линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = getColumn("Тип: ");

        Station station = new Station(name, line, isend, city, type);
        String body = resource.accept(MediaType.TEXT_PLAIN_TYPE, MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Basic " + authStringEnc)
                .entity(station, MediaType.APPLICATION_JSON_TYPE)
                .post(String.class);
        System.out.println(body);
        return Long.parseLong(body);
    }

    private static void printList(List<Station> stations) {
        for (Station station : stations) {
            System.out.println(station);
        }
    }

    private static WebResource setParamIfNotNull(WebResource resource, String paramName, Object value) {
        if (value == null) {
            return resource;
        }
        return resource.queryParam(paramName, value.toString());
    }

    private static String checkNull(String s) {
        return s.length() == 0 ? null : s;
    }

    private static Boolean checkEnd(String s) {
        if (s.length() == 0) return null;
        return s.equals("да") ? Boolean.TRUE : Boolean.FALSE;
    }

    private static String getColumn(String msg) {
        System.out.print(msg);
        try {
            return checkNull(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Boolean getBooaleanColumn(String msg) {
        System.out.print(msg);
        try {
            return checkEnd(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int getIntColumn(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}