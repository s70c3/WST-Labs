
package com.wst.ifmo.com;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "StationException", targetNamespace = "http://com.ifmo.wst.com")
public class StationException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private StationServiceFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public StationException(String message, StationServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public StationException(String message, StationServiceFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.wst.ifmo.com.StationServiceFault
     */
    public StationServiceFault getFaultInfo() {
        return faultInfo;
    }

}