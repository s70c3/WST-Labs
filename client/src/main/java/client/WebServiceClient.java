package client;


import com.wst.ifmo.com.Station;
import com.wst.ifmo.com.StationException;
import com.wst.ifmo.com.StationService;
import com.wst.ifmo.com.StationWebService;
import org.apache.juddi.api_v3.AccessPointType;
import org.uddi.api_v3.*;

import javax.xml.ws.BindingProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;


public class WebServiceClient {

    private static BufferedReader in;
    private static StationService stationService;
    private  static StationWebService service;
    private static JuddiClient juddiClient;

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8081/app/StationService?wsdl");
        stationService = new StationService(url);
        service = stationService.getStationWebServicePort();

        in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter JUDDI username");
        String username = in.readLine().trim();
        System.out.println("Enter JUDDI user password");
        String password = in.readLine().trim();

        juddiClient = new JuddiClient("META-INF/uddi.xml");
        juddiClient.authenticate(username, password);


        System.out.println("Добро пожаловать в систему информации о станциях метро. Что вы хотите сделать?");
        while (true) {
            System.out.println("Введите команду (1 - найти/2 - обновить/3 - удалить/4 - добавить/5 - бизнесы/6 - выйти):");
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
                case 5:
                    command = 0;
                    boolean br = false;
                    while (!br) {
                        switch (command) {
                            case 0:
                                System.out.println("\nВыберите один из пунктов:");
                                System.out.println("1. Вывести все бизнесы");
                                System.out.println("2. Зарегистрировать бизнес");
                                System.out.println("3. Зарегистрировать сервис");
                                System.out.println("4. Найти и использовать сервис");
                                System.out.println("5. Выйти");
                                command = Integer.parseInt(in.readLine());
                                break;
                            case 1:
                                listBusinesses(null);
                                command=0;
                                break;
                            case 2:
                                System.out.println("Введите имя бизнеса");
                                String bnn = readString(in);
                                if (bnn != null) {
                                    createBusiness(bnn);
                                }
                                command = 0;
                                break;
                            case 3:
                                listBusinesses(null);
                                String bbk;
                                do {
                                    System.out.println("Введите ключ бизнеса");
                                    bbk = readString(in);
                                } while (bbk == null);

                                String ssn;
                                do {
                                    System.out.println("Введите имя сервиса");
                                    ssn = readString(in);
                                } while (ssn == null);

                                String ssurl;
                                do {
                                    System.out.println("Введите ссылку на wsdl");
                                    ssurl = readString(in);
                                } while (ssurl == null);
                                createService(bbk, ssn, ssurl);
                                command = 0;
                                break;
                            case 4:
                                System.out.println("Введите имя сервиса для поиска");
                                String ffsn = readString(in);
                                filterServices(ffsn);
                                System.out.println("Введите ключ сервиса");
                                String kkey = readString(in);
                                if (kkey != null) {
                                    useService(kkey);
                                }
                                command = 0;
                                br=true;
                                break;
                            case 5:
                                return;
                            default:
                                command = 0;
                                break;

                        }
                    }
                    break;
                case 6:
                    break;
                default:
                    break;
            }
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

        int status = 0;
        try {
            status = stationService.getStationWebServicePort().update(id, name, city, line, isend, type);
        } catch (StationException e) {
            e.printStackTrace();
        }
        System.out.println(status);
        return status;

    }

    public static int delete() {
        int id = getIntColumn("ID: ");
        int status = 0;
        try {
            status = stationService.getStationWebServicePort().delete(id);
        } catch (StationException e) {
            e.printStackTrace();
        }
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

        long status = 0;
        try {
            status = stationService.getStationWebServicePort().create(name, city, line, isend, type);
        } catch (StationException e) {
            e.printStackTrace();
        }
        System.out.println(status);
        return status;

    }


    private static void useService(String serviceKey) throws RemoteException {

        ServiceDetail serviceDetail = juddiClient.getService(serviceKey.trim());
        if (serviceDetail == null || serviceDetail.getBusinessService() == null || serviceDetail.getBusinessService().isEmpty()) {
            System.out.printf("Can not find service by key '%s'\b", serviceKey);
            return;
        }
        List<BusinessService> services = serviceDetail.getBusinessService();
        BusinessService businessService = services.get(0);
        BindingTemplates bindingTemplates = businessService.getBindingTemplates();
        if (bindingTemplates == null || bindingTemplates.getBindingTemplate().isEmpty()) {
            System.out.printf("No binding template found for service '%s' '%s'\n", serviceKey, businessService.getBusinessKey());
            return;
        }
        for (BindingTemplate bindingTemplate : bindingTemplates.getBindingTemplate()) {
            AccessPoint accessPoint = bindingTemplate.getAccessPoint();
            if (accessPoint.getUseType().equals(AccessPointType.END_POINT.toString())) {
                String value = accessPoint.getValue();
                System.out.printf("Use endpoint '%s'\n", value);
                changeEndpointUrl(value);
                return;
            }
        }
        System.out.printf("No endpoint found for service '%s'\n", serviceKey);
    }


    private static void createService(String businessKey, String serviceName, String wsdlUrl) throws Exception {
        List<ServiceDetail> serviceDetails = juddiClient.publishUrl(businessKey.trim(), serviceName.trim(), wsdlUrl.trim());
        System.out.printf("Services published from wsdl %s\n", wsdlUrl);
        JuddiUtil.printServicesInfo(serviceDetails.stream()
                .map(ServiceDetail::getBusinessService)
                .flatMap(List::stream)
                .collect(Collectors.toList())
        );
    }

    public static void createBusiness(String businessName) throws RemoteException {
        businessName = businessName.trim();
        BusinessDetail business = juddiClient.createBusiness(businessName);
        System.out.println("New business was created");
        for (BusinessEntity businessEntity : business.getBusinessEntity()) {
            System.out.printf("Key: '%s'\n", businessEntity.getBusinessKey());
            System.out.printf("Name: '%s'\n", businessEntity.getName().stream().map(Name::getValue).collect(Collectors.joining(" ")));
        }
    }

    public static void changeEndpointUrl(String endpointUrl) {
        ((BindingProvider) service).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl.trim());
    }

    private static void filterServices(String filterArg) throws RemoteException {
        List<BusinessService> services = juddiClient.getServices(filterArg);
        JuddiUtil.printServicesInfo(services);
    }

    private static void listBusinesses(Void ignored) throws RemoteException {
        JuddiUtil.printBusinessInfo(juddiClient.getBusinessList().getBusinessInfos());
    }

    private static String readString(BufferedReader reader) throws IOException {
        String trim = reader.readLine().trim();
        if (trim.isEmpty()) {
            return null;
        }
        return trim;
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