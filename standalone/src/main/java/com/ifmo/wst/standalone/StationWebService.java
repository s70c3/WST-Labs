package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.SimplePostgresSQLDAO;
import com.ifmo.wst.entity.Station;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "StationService", targetNamespace = "http://com.ifmo.wst.com")
public class StationWebService {
    @WebMethod(operationName = "getStations")
    public List<Station> getStations() {
        SimplePostgresSQLDAO dao = new SimplePostgresSQLDAO();
        List<Station> stations = dao.getAllStations();
        return stations;
    }


}