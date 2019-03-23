package com.ifmo.wst;


import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
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