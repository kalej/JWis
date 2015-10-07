package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "sie")
public class WisItemElement {
    private int id;
    private int docId;
    private int compId;
    private String modified;
    private int instId;
    private String name;
    private List<WisSubElement> subElements;
    private List<WisLink> links;
    private WisDiagnostic diagnostic;
    private WisItem parent;

    public int getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    @XmlAttribute(name="docid")
    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCompId() {
        return compId;
    }

    @XmlAttribute(name="compid")
    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String getModified() {
        return modified;
    }

    @XmlAttribute(name="modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getInstId() {
        return instId;
    }

    @XmlAttribute(name="instid")
    public void setInstId(int instId) {
        this.instId = instId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public List<WisSubElement> getSubElements() {
        return subElements;
    }

    @XmlElement(name="sisub")
    public void setSubElements(List<WisSubElement> subElements) {
        this.subElements = subElements;
    }

    public List<WisLink> getLinks() {
        return links;
    }

    @XmlElement(name="link")
    public void setLinks(List<WisLink> links) {
        this.links = links;
    }

    public WisDiagnostic getDiagnostic() {
        return diagnostic;
    }

    @XmlElement(name = "diagnostic")
    public void setDiagnostic(WisDiagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void scanMap(Map<Integer, String> docMap) {
        docMap.put(docId, name);
    }

    public void setReverseLinks(WisItem wisItem) {
        this.parent = wisItem;

        if ( subElements != null && !subElements.isEmpty() )
            for (WisSubElement subElement : subElements )
                subElement.setParent(this);
    }

    public WisItem getParent() {
        return parent;
    }
}
