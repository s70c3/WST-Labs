package com.ifmo.wst;


import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebService(serviceName = "StationService", targetNamespace = "http://com.ifmo.wst.com")
public class StationWebService {
    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;

    @Inject
    private StationDAO stationDAO;

    @WebMethod(operationName = "findAll")
    public List<Station> findAll() {
        try {
            stationDAO = new StationDAO(getConnection());
            return stationDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @WebMethod(operationName = "filter")
    public List<Station> filter(@WebParam(name = "name")@XmlElement(nillable=true) String name,
                                @WebParam(name = "city")@XmlElement(nillable=true) String city,
                                @WebParam(name = "line")@XmlElement(nillable=true) String line,
                                @WebParam(name = "isend")@XmlElement(nillable=true) Boolean isEnd,
                                @WebParam(name = "type")@XmlElement(nillable=true) String type
    ) {

        try {
            stationDAO = new StationDAO(getConnection());
            return stationDAO.filter(name,  city,  line, isEnd, type); } catch (SQLException e) {
            Logger.getLogger(StationWebService.class.getName()).log(Level.SEVERE, null, e);
          //  e.printStackTrace();
        }
        return  null;
    }


    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StationWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}