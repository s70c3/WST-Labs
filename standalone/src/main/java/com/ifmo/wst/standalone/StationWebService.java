package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.PostgresSQLDAO;
import com.ifmo.wst.entity.Station;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "StationService")
public class StationWebService {
    @WebMethod(operationName = "getStations")
    public List<Station> getStations() {
        PostgresSQLDAO dao = new PostgresSQLDAO();
        List<Station> stations = dao.getAllStations();
        return stations;
    }


}