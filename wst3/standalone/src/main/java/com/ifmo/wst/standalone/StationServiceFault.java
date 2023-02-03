package com.ifmo.wst.standalone;

public class StationServiceFault {
    private static final String DEFAULT_MESSAGE = "Smth went wrong. Please, try again.";
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static StationServiceFault defaultInstance() {
        StationServiceFault fault = new StationServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}