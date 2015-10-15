package ru.kjd.jwis.core.xml;

import ru.kjd.jwis.core.enums.WisItemType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "sc")
public class WisChapter {
    private String name;
    private int id;
    private String num;
    private List<WisItem> items;
    private List<Integer> documents = null;
    private WisSection parent;

    public WisChapter() {
        name = null;
        id = -1;
        num = null;
        items = null;
    }

    public WisChapter(String name, int id, String num, List<WisItem> items) {
        this.name = name;
        this.id = id;
        this.num = num;
        this.items = items;
    }

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

    public List<WisItem> getItems() {
        return items;
    }

    @XmlElement(name = "sit")
    public void setItems(List<WisItem> items) {
        this.items = items;
    }

    public List<Integer> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<>();

            for (WisItem item : items) {
                documents.addAll(item.getDocuments());
            }
        }
        return documents;
    }

    public void scanMap(Map<Integer, String> docMap) {
        for (WisItem item : items)
            item.scanMap(docMap);
    }

    public Set<WisItemType> getTypes() {
        Set<WisItemType> set = new HashSet<>();
        for (WisItem item : items) {
            set.add(item.getType());
        }
        return set;
    }

    public WisChapter filter(WisItemType type) {
        List<WisItem> filteredItems = new ArrayList<>();
        for (WisItem item : items) {
            if (item.getType().equals(type)) {
                filteredItems.add(item);
            }
        }
        return new WisChapter(name, id, num, filteredItems);
    }

    public void setReverseLinks(WisSection wisSection) {
        this.parent = wisSection;

        for (WisItem item : items)
            item.setReverseLinks(this);
    }
}
