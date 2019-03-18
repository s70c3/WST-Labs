
package generated;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "StationWebService", targetNamespace = "http://com.ifmo.wst.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StationWebService {


    /**
     * 
     * @param deepness
     * @param startworkhour
     * @param startworkhourminute
     * @param endworkhour
     * @param line
     * @param name
     * @param id
     * @param endworkhourminute
     * @param isend
     * @return
     *     returns java.util.List<Station>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "filter", targetNamespace = "http://com.ifmo.wst.com", className = "Filter")
    @ResponseWrapper(localName = "filterResponse", targetNamespace = "http://com.ifmo.wst.com", className = "FilterResponse")
    public List<Station> filter(
        @WebParam(name = "id", targetNamespace = "")
        Integer id,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "deepness", targetNamespace = "")
        Integer deepness,
        @WebParam(name = "line", targetNamespace = "")
        Integer line,
        @WebParam(name = "isend", targetNamespace = "")
        Boolean isend,
        @WebParam(name = "startworkhour", targetNamespace = "")
        Integer startworkhour,
        @WebParam(name = "startworkhourminute", targetNamespace = "")
        Integer startworkhourminute,
        @WebParam(name = "endworkhour", targetNamespace = "")
        Integer endworkhour,
        @WebParam(name = "endworkhourminute", targetNamespace = "")
        Integer endworkhourminute);

    /**
     * 
     * @return
     *     returns java.util.List<Station>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStations", targetNamespace = "http://com.ifmo.wst.com", className = "GetStations")
    @ResponseWrapper(localName = "getStationsResponse", targetNamespace = "http://com.ifmo.wst.com", className = "GetStationsResponse")
    public List<Station> getStations();

    /**
     * 
     * @return
     *     returns java.util.List<Station>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://com.ifmo.wst.com", className = "FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://com.ifmo.wst.com", className = "FindAllResponse")
    public List<Station> findAll();

}
