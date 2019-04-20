package com.ifmo.wst.standalone;

import com.ifmo.wst.dao.StationDAO;
import com.ifmo.wst.entity.Station;
import sun.misc.BASE64Decoder;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<Station> getStationsByName(@PathParam("name") String name,
                                           @HeaderParam("authorization") String authString) throws
            StationException {
        if(!isUserAuthenticated(authString)){
            throw new StationException("Invalid user");
        }
        StationDAO stationDAO = new StationDAO();
        if (name == null || name.trim().isEmpty())
            throw StationException.DEFAULT_INSTANCE;
        return  stationDAO.findByName(name); }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Station station, @Context UriInfo uriInfo, @HeaderParam("authorization") String authString) throws StationException{
        StationDAO stationDAO = new StationDAO();
        if(!isUserAuthenticated(authString)){
            throw new StationException("Invalid user");
        }
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
    public String delete(@PathParam("id") long id,
                         @HeaderParam("authorization") String authString) throws StationException {
        if(!isUserAuthenticated(authString)){
            throw new StationException("Invalid user");
        }
        StationDAO stationDAO = new StationDAO();
        if (stationDAO.findById(id) == null) {
            throw new StationException("No station with this id. Try another.");
        }
        return String.valueOf(stationDAO.delete(id));
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String update(@PathParam("id") long id, Station station,
                         @HeaderParam("authorization") String authString) throws StationException {
        if(!isUserAuthenticated(authString)){
            throw new StationException("Invalid user");
        }
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

    private boolean isUserAuthenticated(String authString){

        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string

        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {

            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        Logger.getLogger(StationResource.class.getName()).log(Level.SEVERE, decodedAuth);

        String[] up = decodedAuth.split(":");

        String user = up[0];
        String pass = up[1];

        boolean authenticationStatus = user.equals("user")
                && pass.equals("pass");
        return authenticationStatus;
    }


}