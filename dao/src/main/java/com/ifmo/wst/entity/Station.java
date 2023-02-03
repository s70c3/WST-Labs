
package com.ifmo.wst.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Station {
  //  @XmlElement(name="id")
    private int id;
    //@XmlElement(name="name")
    private String name;
    //@XmlElement(name="isend")
    private Boolean isEnd;
 //   @XmlElement(name="city")
    private String city;
   // @XmlElement(name="line")
    private String line;
 //   @XmlElement(name="type")
    private String station_type;

    public Station() {
    }

    public Station(String name, String line, Boolean isEnd, String station_type, String city) {
        this.name = name;
        this.isEnd = isEnd;
        this.city = city;
        this.line = line;
        this.station_type = station_type;
    }

    public Station(int id, String name, String line, Boolean isEnd, String station_type, String city) {
        this.id = id;
        this.name = name;
        this.isEnd = isEnd;
        this.city = city;
        this.line = line;
        this.station_type = station_type;
    }

    public int getId() { return id; }

    public void setId(int id) {  this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStation_type() {
        return station_type;
    }

    public void setStation_type(String station_type) {
        this.station_type = station_type;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", isEnd=" + isEnd +
                ", city='" + city + '\'' +
                ", line='" + line + '\'' +
                ", station_type='" + station_type + '\'' +
                '}';
    }
}
