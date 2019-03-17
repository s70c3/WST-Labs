
package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for station complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="station">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deepness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="endWorkHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endWorkMinute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="line" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startWorkHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startWorkMinute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "station", propOrder = {
    "deepness",
    "end",
    "endWorkHour",
    "endWorkMinute",
    "id",
    "line",
    "name",
    "startWorkHour",
    "startWorkMinute"
})
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

}
