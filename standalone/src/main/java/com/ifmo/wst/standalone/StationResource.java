package com.ifmo.wst.standalone;

import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
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

    @Path("/{name}")
    @GET
    public List<Station> getStationsByName(@PathParam("name") String name) throws
            StationException {
        StationDAO stationDAO = new StationDAO();
        if (name == null || name.trim().isEmpty())
            throw StationException.DEFAULT_INSTANCE;
        return  stationDAO.findByName(name); }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Station station, @Context UriInfo uriInfo) throws StationException{
        StationDAO stationDAO = new StationDAO();
        if (!station.getName().matches("[a-zA-Zа-яА-Я]+")) {
            throw new StationException("Station name should contain only letters.");
        }
        long createdId = stationDAO.create(station.getName(), station.getEnd(), station.getCity(), station.getLine(),
                station.getStation_type());
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(String.valueOf(createdId));
        return Response.created(builder.build()).entity(String.valueOf(createdId)).build();
    }


    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String delete(@PathParam("id") long id) throws StationException {
        StationDAO stationDAO = new StationDAO();
        if (stationDAO.findById(id) == null) {
            throw new StationException("No station with this id. Try another.");
        }
        return String.valueOf(stationDAO.delete(id));
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String update(@PathParam("id") long id, Station station) throws StationException {
        StationDAO stationDAO = new StationDAO();
        if (stationDAO.findById(id) == null) {
            throw new StationException("No station with this id. Try another.");
        }
        if (!station.getName().matches("[a-zA-Zа-яА-Я]+")) {
            throw new StationException("Station name should contain only letters.");
        }
        return String.valueOf(stationDAO.update(id, station.getName(), station.getEnd(),
                station.getCity(), station.getLine(), station.getStation_type()));
    }


}