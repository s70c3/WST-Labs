package com.ifmo.wst.standalone;

public class StationException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;
    public static StationException DEFAULT_INSTANCE = new StationException("please check the parameters");

    public StationException(String message) {
        super(message);
    }


}
