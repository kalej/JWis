package ru.kjd.jwis.core.xml;

import ru.kjd.jwis.core.enums.WisItemType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by anonymous on 9/29/15.
 */
@XmlRootElement(name = "sit")
public class WisItem {
    private List<Integer> documents = null;
    private String name;
    private  WisChapter parent;
    private int id;

    private String num;

    private List<WisItemElement> elements;

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    @XmlAttribute(name = "num")
    public void setNum(String num) {
        this.num = num;
    }

    public List<WisItemElement> getElements() {
        return elements;
    }

    @XmlElement(name = "sie")
    public void setElements(List<WisItemElement> elements) {
        this.elements = elements;
    }

    public List<Integer> getDocuments() {
        if ( documents == null ){
            documents = new ArrayList<>();

            for ( WisItemElement itemElement : elements ){
                documents.add(itemElement.getDocId());
            }
        }
        return documents;
    }

    public void scanMap(Map<Integer, String> docMap) {
        for ( WisItemElement element : elements )
            element.scanMap(docMap);
    }

    public WisItemType getType(){
        return WisItemType.valueOf(Integer.parseInt(num.substring(0, 2)));
    }

    public void setReverseLinks(WisChapter chapter) {
        this.parent = chapter;

        for ( WisItemElement itemElement : elements )
            itemElement.setReverseLinks(this);
    }

    public WisChapter getParent() {
        return parent;
    }
}
