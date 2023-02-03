package com.ifmo.wst.standalone;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StationExceptionMapper implements ExceptionMapper<StationException> {
    @Override
    public Response toResponse(StationException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
