
package com.ifmo.wst.entity;

public class Station {

    private String name;
    private Boolean isEnd;
    private String city;
    private String line;
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
                "name='" + name + '\'' +
                ", isEnd=" + isEnd +
                ", city='" + city + '\'' +
                ", line='" + line + '\'' +
                ", station_type='" + station_type + '\'' +
                '}';
    }
}
