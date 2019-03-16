
package com.ifmo.wst.entity;

public class Station {

    protected int deepness;
    protected boolean end;
    protected int endWorkHour;
    protected int endWorkMinute;
    protected int id;
    protected int line;
    protected String name;
    protected int startWorkHour;
    protected int startWorkMinute;

    public Station() {
    }

    public Station(int id,String name,  int line,   int deepness, boolean isEnd, int startWorkHour, int startWorkMinute, int endWorkHour, int endWorkMinute) {
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

    /**
     * Gets the value of the deepness property.
     *
     */
    public int getDeepness() {
        return deepness;
    }

    /**
     * Sets the value of the deepness property.
     *
     */
    public void setDeepness(int value) {
        this.deepness = value;
    }

    /**
     * Gets the value of the end property.
     *
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     *
     */
    public void setEnd(boolean value) {
        this.end = value;
    }

    /**
     * Gets the value of the endWorkHour property.
     *
     */
    public int getEndWorkHour() {
        return endWorkHour;
    }

    /**
     * Sets the value of the endWorkHour property.
     *
     */
    public void setEndWorkHour(int value) {
        this.endWorkHour = value;
    }

    /**
     * Gets the value of the endWorkMinute property.
     *
     */
    public int getEndWorkMinute() {
        return endWorkMinute;
    }

    /**
     * Sets the value of the endWorkMinute property.
     *
     */
    public void setEndWorkMinute(int value) {
        this.endWorkMinute = value;
    }

    /**
     * Gets the value of the id property.
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the line property.
     *
     */
    public int getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     *
     */
    public void setLine(int value) {
        this.line = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the startWorkHour property.
     *
     */
    public int getStartWorkHour() {
        return startWorkHour;
    }

    /**
     * Sets the value of the startWorkHour property.
     *
     */
    public void setStartWorkHour(int value) {
        this.startWorkHour = value;
    }

    /**
     * Gets the value of the startWorkMinute property.
     *
     */
    public int getStartWorkMinute() {
        return startWorkMinute;
    }

    /**
     * Sets the value of the startWorkMinute property.
     *
     */
    public void setStartWorkMinute(int value) {
        this.startWorkMinute = value;
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
