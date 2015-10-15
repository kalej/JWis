package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "link")
public class WisLink {
    private int linkId;
    private String dest;

    public int getLinkId() {
        return linkId;
    }

    @XmlAttribute(name = "linkid")
    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getDest() {
        return dest;
    }

    @XmlAttribute(name = "dest")
    public void setDest(String dest) {
        this.dest = dest;
    }

}
