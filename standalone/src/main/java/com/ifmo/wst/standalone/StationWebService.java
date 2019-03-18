package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.SimplePostgresSQLDAO;
import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "StationService", targetNamespace = "http://com.ifmo.wst.com")
public class StationWebService {


    @Inject
    private StationDAO stationDAO;


    @WebMethod(operationName = "getStations")
    public List<Station> getStations() {
        SimplePostgresSQLDAO dao = new SimplePostgresSQLDAO();
        List<Station> stations = dao.getAllStations();
        return stations;
    }

    @WebMethod(operationName = "findAll")
    public List<Station> findAll() {
        try {
            return stationDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod(operationName = "filter")
    public List<Station> filter(@WebParam(name = "id") Integer id, @WebParam(name = "name") String name,
                                @WebParam(name = "deepness") Integer deepness, @WebParam(name = "line") Integer line,
                                @WebParam(name = "isend") Boolean isEnd,
                                @WebParam(name = "startworkhour") Integer startworkhour,
                                @WebParam(name = "startworkhourminute") Integer startworkhourminute,
                                @WebParam(name = "endworkhour") Integer endworkhour,
                                @WebParam(name = "endworkhourminute") Integer endworkhourminute) {
        try {
            return stationDAO.filter(id, name, deepness, line, isEnd, startworkhour, startworkhourminute, endworkhour, endworkhourminute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public StationWebService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public StationWebService() {
        this.stationDAO = new StationDAO();
    }


}

