package com.ifmo.wst.standalone;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.wishmaster.ifmo.ws.jaxws.errors.PersonServiceFault")
public class StationException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    private final StationServiceFault fault;

    public StationException(String message, StationServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public StationException(String message, StationServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public StationServiceFault getFaultInfo() {
        return fault;
    }
}

