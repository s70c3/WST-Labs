
package com.wst.ifmo.com;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wst.ifmo.com package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FilterResponse_QNAME = new QName("http://com.ifmo.wst.com", "filterResponse");
    private final static QName _FindAllResponse_QNAME = new QName("http://com.ifmo.wst.com", "findAllResponse");
    private final static QName _GetStations_QNAME = new QName("http://com.ifmo.wst.com", "getStations");
    private final static QName _FindAll_QNAME = new QName("http://com.ifmo.wst.com", "findAll");
    private final static QName _Filter_QNAME = new QName("http://com.ifmo.wst.com", "filter");
    private final static QName _GetStationsResponse_QNAME = new QName("http://com.ifmo.wst.com", "getStationsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wst.ifmo.com
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link GetStationsResponse }
     * 
     */
    public GetStationsResponse createGetStationsResponse() {
        return new GetStationsResponse();
    }

    /**
     * Create an instance of {@link FilterResponse }
     * 
     */
    public FilterResponse createFilterResponse() {
        return new FilterResponse();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link GetStations }
     * 
     */
    public GetStations createGetStations() {
        return new GetStations();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link Station }
     * 
     */
    public Station createStation() {
        return new Station();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "filterResponse")
    public JAXBElement<FilterResponse> createFilterResponse(FilterResponse value) {
        return new JAXBElement<FilterResponse>(_FilterResponse_QNAME, FilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "getStations")
    public JAXBElement<GetStations> createGetStations(GetStations value) {
        return new JAXBElement<GetStations>(_GetStations_QNAME, GetStations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Filter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "filter")
    public JAXBElement<Filter> createFilter(Filter value) {
        return new JAXBElement<Filter>(_Filter_QNAME, Filter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.ifmo.wst.com", name = "getStationsResponse")
    public JAXBElement<GetStationsResponse> createGetStationsResponse(GetStationsResponse value) {
        return new JAXBElement<GetStationsResponse>(_GetStationsResponse_QNAME, GetStationsResponse.class, null, value);
    }

}
