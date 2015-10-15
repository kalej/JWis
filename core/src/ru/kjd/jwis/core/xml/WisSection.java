package ru.kjd.jwis.core.xml;

import ru.kjd.jwis.core.enums.WisItemType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "sct")
public class WisSection {
    private String name;
    private int id;
    private String num;
    private List<WisChapter> chapters;
    private List<Integer> documents = null;
    private WisHierarchy parent;

    public WisSection() {
        name = null;
        id = -1;
        num = null;
        chapters = null;
    }

    public WisSection(String name, int id, String num, List<WisChapter> chapters) {
        this.name = name;
        this.id = id;
        this.num = num;
        this.chapters = chapters;
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

    public List<WisChapter> getChapters() {
        return chapters;
    }

    @XmlElement(name = "sc")
    public void setChapters(List<WisChapter> chapters) {
        this.chapters = chapters;
    }

    public List<Integer> getDocuments() {
        if (documents == null) {
            documents = new ArrayList<>();

            for (WisChapter chapter : chapters) {
                documents.addAll(chapter.getDocuments());
            }
        }
        return documents;
    }

    public void scanMap(Map<Integer, String> docMap) {
        for (WisChapter chapter : chapters) {
            chapter.scanMap(docMap);
        }
    }

    public Set<WisItemType> getTypes() {
        Set<WisItemType> set = new HashSet<>();
        for (WisChapter chapter : chapters) {
            set.addAll(chapter.getTypes());
        }
        return set;
    }

    public WisSection filter(WisItemType type) {
        List<WisChapter> filteredChapters = new ArrayList<>();
        for (WisChapter chapter : chapters) {
            if (chapter.getTypes().contains(type)) {
                filteredChapters.add(chapter.filter(type));
            }
        }
        return new WisSection(name, id, num, filteredChapters);
    }

    public void setReverseLinks(WisHierarchy hierarchy) {
        this.parent = hierarchy;

        for (WisChapter chapter : chapters)
            chapter.setReverseLinks(this);
    }

    public WisHierarchy getParent() {
        return parent;
    }
}
