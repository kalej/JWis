package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private String type;
    private String markets;

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    public String getMarkets() {
        return markets;
    }

    @XmlAttribute(name = "markets")
    public void setMarkets(String markets) {
        this.markets = markets;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    @XmlAttribute(name = "docid")
    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCompId() {
        return compId;
    }

    @XmlAttribute(name = "compid")
    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String getModified() {
        return modified;
    }

    @XmlAttribute(name = "modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getInstId() {
        return instId;
    }

    @XmlAttribute(name = "instid")
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

    @XmlElement(name = "sisub")
    public void setSubElements(List<WisSubElement> subElements) {
        this.subElements = subElements;
    }

    public List<WisLink> getLinks() {
        return links;
    }

    @XmlElement(name = "link")
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

        if (subElements != null && !subElements.isEmpty())
            for (WisSubElement subElement : subElements)
                subElement.setParent(this);
    }

    public WisItem getParent() {
        return parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append("; type: ").append(type);
        sb.append("; docId: ").append(docId);
        sb.append("; compId: ").append(compId);
        sb.append("; instId:").append(instId);

        return sb.toString();
    }

    public boolean isApplicable(String market){
        if ( markets == null || markets.isEmpty() )
            return true;

        for( String mkt : markets.split(" ") ){
            if ( market.equals(mkt ) )
                return true;
        }
        return false;
    }

    public String getDestId(int linkId){
        for (WisLink link : links) {
                if (link.getLinkId() == linkId)
                    return link.getDest();
        }

        return null;
    }

    public int getDocId(int dest) {
        for (WisItemElement itemElement : parent.getElements()) {
            if (itemElement.getId() == dest)
                return itemElement.getDocId();
        }

        return -1;
    }
}
