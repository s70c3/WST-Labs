package com.ifmo.wst.standalone;

import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stations")
@Produces({MediaType.APPLICATION_JSON})
public class StationResource {

    @GET
    public List<Station> read(@QueryParam("name") String name, @QueryParam("city") String city,
                              @QueryParam("isend") Boolean isEnd, @QueryParam("type") String type,
                              @QueryParam("line") String line) {
        StationDAO stationDAO = new StationDAO();
        return stationDAO.read(name, city, line, isEnd, type);
    }



}