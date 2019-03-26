package com.ifmo.wst.standalone;


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
    public List<Station> read(@WebParam(name = "name") String name,
                              @WebParam(name = "city") String city,
                              @WebParam(name = "line") String line,
                              @WebParam(name = "isend") Boolean isEnd,
                              @WebParam(name = "type") String type
    ) {

        try {

            return stationDAO.read(name, city, line, isEnd, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @WebMethod(operationName = "create")
    public long create(@WebParam(name = "name") String name,
                       @WebParam(name = "city") String city,
                       @WebParam(name = "line") String line,
                       @WebParam(name = "isend") Boolean isEnd,
                       @WebParam(name = "type") String type
    ) {
        return stationDAO.create(name, isEnd, line, city, type);
    }

    @WebMethod(operationName = "delete")
    public int delete(@WebParam(name = "id") int id
    ) {
        return stationDAO.delete(id);
    }

    @WebMethod(operationName = "update")
    public int update(@WebParam(name = "id") int id,
                      @WebParam(name = "name") String name,
                      @WebParam(name = "city") String city,
                      @WebParam(name = "line") String line,
                      @WebParam(name = "isend") Boolean isEnd,
                      @WebParam(name = "type") String type
    ) {
        return stationDAO.update(id, name, isEnd, line, city, type);

    }


    public StationWebService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public StationWebService() {
        this.stationDAO = new StationDAO();
    }


}
