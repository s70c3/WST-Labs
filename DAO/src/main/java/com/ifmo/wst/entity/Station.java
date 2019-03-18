
package com.ifmo.wst.entity;

public class Station {

    private Integer deepness;
    private Boolean end;
    private Integer endWorkHour;
    private Integer endWorkMinute;
    private Integer id;
    private Integer line;
    private String name;
    private Integer startWorkHour;
    private Integer startWorkMinute;

    public Station() {
    }

    public Station(Integer id, String name, Integer line, Integer deepness, Boolean isEnd, Integer startWorkHour, Integer startWorkMinute, Integer endWorkHour, Integer endWorkMinute) {
        this.deepness = deepness;
        this.end = isEnd;
        this.endWorkHour = endWorkHour;
        this.endWorkMinute = endWorkMinute;
        this.id = id;
        this.line = line;
        this.name = name;
        this.startWorkHour = startWorkHour;
        this.startWorkMinute = startWorkMinute;
    }

    public Integer getDeepness() {
        return deepness;
    }

    public void setDeepness(Integer deepness) {
        this.deepness = deepness;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Integer getEndWorkHour() {
        return endWorkHour;
    }

    public void setEndWorkHour(Integer endWorkHour) {
        this.endWorkHour = endWorkHour;
    }

    public Integer getEndWorkMinute() {
        return endWorkMinute;
    }

    public void setEndWorkMinute(Integer endWorkMinute) {
        this.endWorkMinute = endWorkMinute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartWorkHour() {
        return startWorkHour;
    }

    public void setStartWorkHour(Integer startWorkHour) {
        this.startWorkHour = startWorkHour;
    }

    public Integer getStartWorkMinute() {
        return startWorkMinute;
    }

    public void setStartWorkMinute(Integer startWorkMinute) {
        this.startWorkMinute = startWorkMinute;
    }

    @Override
    public String toString() {
        return "Station{" +
                "deepness=" + deepness +
                ", end=" + end +
                ", endWorkHour=" + endWorkHour +
                ", endWorkMinute=" + endWorkMinute +
                ", id=" + id +
                ", line=" + line +
                ", name='" + name + '\'' +
                ", startWorkHour=" + startWorkHour +
                ", startWorkMinute=" + startWorkMinute +
                '}';
    }
}
