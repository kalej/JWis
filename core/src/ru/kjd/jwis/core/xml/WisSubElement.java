package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sisub")
public class WisSubElement {
    private int id;
    private int siSubId;
    private String name;
    private WisItemElement parent;

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public int getSiSubId() {
        return siSubId;
    }

    @XmlAttribute(name = "sisubid")
    public void setSiSubId(int siSubId) {
        this.siSubId = siSubId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public WisItemElement getParent() {
        return parent;
    }

    public void setParent(WisItemElement parent) {
        this.parent = parent;
    }
}
