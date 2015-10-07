package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@XmlRootElement( name = "modelyear" )
public class WisHierarchy {
    private String carModel;
    private int modelNumber;
    private int modelYear;
    private String guiType;
    private int version;
    private String chassi;
    private String clientVersion;
    private String relDate;
    private String prevRelDate;
    private String language;
    List<WisSection> sections;

    private Map<Integer, String> docMap;

    @XmlAttribute( name = "carmodel", required=true)
    public void setCarModel(String carModel){
        this.carModel = carModel;
    }

    @XmlAttribute( name = "modelnumber", required=true )
    public void setModelNumber(int modelNumber){
        this.modelNumber = modelNumber;
    }

    @XmlAttribute( name = "modelyear" )
    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    @XmlAttribute( name = "guitype" )
    public void setGuiType(String guiType) {
        this.guiType = guiType;
    }

    @XmlAttribute( name = "version" )
    public void setVersion(int version) {
        this.version = version;
    }

    @XmlAttribute( name = "chassi" )
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    @XmlAttribute( name = "clientversion" )
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    @XmlAttribute( name = "reldate" )
    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    @XmlAttribute( name = "prevreldate" )
    public void setPrevRelDate(String prevRelDate) {
        this.prevRelDate = prevRelDate;
    }

    @XmlAttribute( name = "language" )
    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement( name = "sct" )
    public void setSections(List<WisSection> sections) {
        this.sections = sections;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public int getModelYear() {
        return modelYear;
    }

    public String getGuiType() {
        return guiType;
    }

    public int getVersion() {
        return version;
    }

    public String getChassi() {
        return chassi;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getRelDate() {
        return relDate;
    }

    public String getPrevRelDate() {
        return prevRelDate;
    }

    public String getLanguage() {
        return language;
    }

    public List<WisSection> getSections() {
        return sections;
    }

    public void scanDocs(Map<Integer, String> docMap) {
        for ( WisSection section : sections )
            section.scanMap(docMap);
    }

    public String getDocTitle(int docId){
        if ( docMap == null ) {
            docMap = new TreeMap<>();
            scanDocs(docMap);
        }
        return docMap.get(docId);
    }

    public void setReverseLinks() {
        for (WisSection section : sections){
            section.setReverseLinks(this);
        }
    }
}