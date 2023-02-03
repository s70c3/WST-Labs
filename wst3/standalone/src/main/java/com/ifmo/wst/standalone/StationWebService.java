package com.ifmo.wst.standalone;


import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "StationService", targetNamespace = "http://com.ifmo.wst.com")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public class StationWebService {


    @Inject
    private StationDAO stationDAO;

    @Resource
    WebServiceContext wsctx;

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
    ) throws StationException {
        if(!checkAuth()) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Invalid user", fault);
        }
        return stationDAO.read(name, city, line, isEnd, type);
    }


    @WebMethod(operationName = "create")
    public long create(@WebParam(name = "name") String name,
                       @WebParam(name = "city") String city,
                       @WebParam(name = "line") String line,
                       @WebParam(name = "isend") Boolean isEnd,
                       @WebParam(name = "type") String type
    ) throws StationException {
        if(!checkAuth()) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Invalid user", fault);
        }
        if (!name.matches("[a-zA-Zа-яА-Я]+")) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Station name should contain only letters.", fault);
        }
        return stationDAO.create(name, isEnd, line, city, type);
    }

    @WebMethod(operationName = "delete")
    public int delete(@WebParam(name = "id") int id
    ) throws StationException {
        if(!checkAuth()) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Invalid user", fault);
        }
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

        if(!checkAuth()) {
            StationServiceFault fault = StationServiceFault.defaultInstance();
            throw new StationException("Invalid user", fault);
        }
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

    private boolean checkAuth() throws StationException {
        MessageContext mctx = wsctx.getMessageContext();

        Logger.getLogger(StationWebService.class.getName()).log(Level.SEVERE, wsctx.getMessageContext().toString());


        //get detail from request headers
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        Logger.getLogger(StationWebService.class.getName()).log(Level.SEVERE, "user"+((Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS)).values());


        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        if (userList== null || userList.isEmpty()) {
            throw new StationException("No authorization header", StationServiceFault.defaultInstance());
        }

        String username = "";
        String password = "";

        if(userList!=null){
            //get username
            username = userList.get(0).toString();
        }

        if(passList!=null){
            //get password
            password = passList.get(0).toString();
        }

        //Should validate username and password with database
        if (username.equals("user") && password.equals("pass")){
            return true;
        }else{
            return false;
        }

    }
}

