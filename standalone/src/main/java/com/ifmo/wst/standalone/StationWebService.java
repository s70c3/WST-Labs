package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "StationService", targetNamespace = "http://com.ifmo.wst.com")
public class StationWebService {


    @Inject
    private StationDAO stationDAO;


    @WebMethod(operationName = "findAll")
    public List<Station> findAll() {
            return stationDAO.findAll();

    }


    @WebMethod(operationName = "read")
    public List<Station> read(@WebParam(name = "name") String name,
                              @WebParam(name = "city") String city,
                              @WebParam(name = "line") String line,
                              @WebParam(name = "isend") Boolean isEnd,
                              @WebParam(name = "type") String type
    ) {
        return stationDAO.read(name, city, line, isEnd, type);
    }


    @WebMethod(operationName = "create")
    public long create(@WebParam(name = "name") String name,
                       @WebParam(name = "city") String city,
                       @WebParam(name = "line") String line,
                       @WebParam(name = "isend") Boolean isEnd,
                       @WebParam(name = "type") String type
    ) throws StationException {
        if (!name.matches("[a-zA-Zа-яА-Я]+")) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Station name should contain only letters.", fault);
        }
        return stationDAO.create(name, isEnd, line, city, type);
    }

    @WebMethod(operationName = "delete")
    public int delete(@WebParam(name = "id") int id
    ) throws StationException {
        if (stationDAO.findById(id) == null) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("No station with this id. Try another.", fault);
        }

        return stationDAO.delete(id);
    }

    @WebMethod(operationName = "update")
    public int update(@WebParam(name = "id") int id,
                      @WebParam(name = "name") String name,
                      @WebParam(name = "city") String city,
                      @WebParam(name = "line") String line,
                      @WebParam(name = "isend") Boolean isEnd,
                      @WebParam(name = "type") String type
    ) throws StationException {
        if (stationDAO.findById(id) == null) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("No station with this id. Try another.", fault);
        }
        if (!name.matches("[a-zA-Zа-яА-Я]+")) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Station name should contain only letters.", fault);
        }

        return stationDAO.update(id, name, isEnd, line, city, type);

    }


    public StationWebService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public StationWebService() {
        this.stationDAO = new StationDAO();
    }


}
