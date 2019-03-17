package com.ifmo.wst;



import com.ifmo.wst.dao.PostgresSQLDAO;
import com.ifmo.wst.entity.Station;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebService(serviceName = "StationService")
public class StationWebService {
    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;

    @WebMethod(operationName = "getAllStations")
    public List<Station> getAllPersons() {
        PostgresSQLDAO dao = new PostgresSQLDAO(getConnection());
        return dao.getAllStations();
    }

    @WebMethod(operationName = "getStationByName")
    public Station getStationByName(@WebParam(name = "stationName") String name) {
        PostgresSQLDAO dao = new PostgresSQLDAO(getConnection());
        return dao.getStationByName(name);
    }

    @WebMethod(operationName = "getStationByLine")
    public List<Station> getStationByLine(@WebParam(name = "stationLine") int line) {
        PostgresSQLDAO dao = new PostgresSQLDAO(getConnection());
        return dao.getStationsByLine(line);
    }
    @WebMethod(operationName = "getSmthBySmth")
    public List<Station> getSmthBySmth(@WebParam(name = "stationLine") int line) {
        PostgresSQLDAO dao = new PostgresSQLDAO(getConnection());
        return dao.getStationsByLine(line);
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