package client;


import com.ifmo.wst.entity.Station;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WebServiceClient {

    private static BufferedReader in;
    private static final String URL = "http://localhost:8080/rest/stations";

    public static void main(String[] args) throws IOException {

        Client client;

        try {
            client = new Client();
            WebResource resource = client.resource(URL);
            resource.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Station>>(){});
            System.out.println("client standalone");
        }
        catch (javax.ws.rs.WebApplicationException e) {
            ClientConfig cfg = new DefaultClientConfig();
            cfg.getClasses().add(JacksonJsonProvider.class);
            client = Client.create(cfg);
            System.out.println("client j2ee");
         }

        System.out.println("Добро пожаловать в систему информации о станциях метро. Что вы хотите сделать?");
        in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            printList(read(client));
        }
    }


    public static List<Station> read(Client client) {

        System.out.println("Найдите интересующую станцию. Параметры поиска:");

        String name = getColumn("Название станции: ");
        String city = getColumn("Город: ");
        String line = getColumn("Линия: ");
        Boolean isend = getBooaleanColumn("Является конечной (да/нет): ");
        String type = getColumn("Тип: ");


        WebResource resource = client.resource(URL);
        resource = setParamIfNotNull(resource, "name", name);
        resource = setParamIfNotNull(resource, "city", city);
        resource = setParamIfNotNull(resource, "line", line);
        resource = setParamIfNotNull(resource, "isend", isend);
        resource = setParamIfNotNull(resource, "type", type);

        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Station>> stations = new GenericType<List<Station>>() {
        };

        return  resource.accept(MediaType.APPLICATION_JSON).get(stations);

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