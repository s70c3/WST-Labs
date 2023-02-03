package com.ifmo.wst.standalone;


import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        String url = "http://0.0.0.0:8080/app/StationService";
        StationWebService stws = new StationWebService();
        Endpoint.publish(url, stws);
    }
}