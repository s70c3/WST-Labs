
package com.ifmo.wst.standalone;


import com.ifmo.wst.entity.Station;
import com.ifmo.wst.util.PostgresSQLDAO;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "StationService")
public class StationWebService {
    @WebMethod(operationName = "getStations")
    public List<Station> getStations() {
        PostgresSQLDAO dao = new PostgresSQLDAO();
        List<Station> stations = dao.getStations();
        return stations;
    }


}