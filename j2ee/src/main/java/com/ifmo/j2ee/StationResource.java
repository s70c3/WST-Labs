package com.ifmo.j2ee;


import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("/stations")
@Produces({MediaType.APPLICATION_JSON})
public class StationResource {
    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;

    @GET
    public List<Station> read(@QueryParam("name") String name, @QueryParam("city") String city,
                              @QueryParam("isend") Boolean isEnd, @QueryParam("type") String type,
                              @QueryParam("line") String line) {
        StationDAO stationDAO = new StationDAO(getConnection());
        List<Station> stations = stationDAO.read(name, city, line, isEnd, type);
        Logger.getLogger(StationResource.class.getName()).log(Level.SEVERE, "hello");
        return stations;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}