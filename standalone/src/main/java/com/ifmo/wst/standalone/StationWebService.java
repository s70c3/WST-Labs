package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.SimplePostgresSQLDAO;
import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
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

    @WebMethod(operationName = "read")
    public List<Station> read(@WebParam(name = "name")@XmlElement(nillable=true) String name,
                                @WebParam(name = "city")@XmlElement(nillable=true) String city,
                                @WebParam(name = "line")@XmlElement(nillable=true) String line,
                                @WebParam(name = "isend")@XmlElement(nillable=true) Boolean isEnd,
                                @WebParam(name = "type")@XmlElement(nillable=true) String type
                               ) {

        try {

            return stationDAO.read(name,  city,  line, isEnd, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }


    @WebMethod(operationName = "create")
    public List<Station> create(@WebParam(name = "name")@XmlElement(nillable=true) String name,
                              @WebParam(name = "city")@XmlElement(nillable=true) String city,
                              @WebParam(name = "line")@XmlElement(nillable=true) String line,
                              @WebParam(name = "isend")@XmlElement(nillable=true) Boolean isEnd,
                              @WebParam(name = "type")@XmlElement(nillable=true) String type
    ) {

        try {

            return stationDAO.create(name,  city,  line, isEnd, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @WebMethod(operationName = "delete")
    public List<Station> delete(@WebParam(name = "name")@XmlElement(nillable=true) String name,
                                @WebParam(name = "city")@XmlElement(nillable=true) String city,
                                @WebParam(name = "line")@XmlElement(nillable=true) String line,
                                @WebParam(name = "isend")@XmlElement(nillable=true) Boolean isEnd,
                                @WebParam(name = "type")@XmlElement(nillable=true) String type
    ) {

        try {

            return stationDAO.delete(name,  city,  line, isEnd, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    @WebMethod(operationName = "update")
    public List<Station> update(@WebParam(name = "name")@XmlElement(nillable=true) String name,
                                @WebParam(name = "city")@XmlElement(nillable=true) String city,
                                @WebParam(name = "line")@XmlElement(nillable=true) String line,
                                @WebParam(name = "isend")@XmlElement(nillable=true) Boolean isEnd,
                                @WebParam(name = "type")@XmlElement(nillable=true) String type
    ) {

        try {

            return stationDAO.read(name,  city,  line, isEnd, type);
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
