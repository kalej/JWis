package ru.kjd.jwis.jwisfx;

import ru.kjd.jwis.core.xml.*;

/**
 * Created by Николай on 28.10.2015.
 */
public class JWisHistory {
    private static WisHierarchy currentHierarchy;
    private static WisSection currentSection;
    private static WisChapter currentChapter;
    private static WisItem currentItem;
    private static WisItemElement currentElement;
    private static WisSubElement currentSubelement;

    public static WisHierarchy getCurrentHierarchy() {
        return currentHierarchy;
    }

    public static void setCurrentHierarchy(WisHierarchy currentHierarchy) {
        JWisHistory.currentHierarchy = currentHierarchy;
    }

    public static WisSection getCurrentSection() {
        return currentSection;
    }

    public static void setCurrentSection(WisSection currentSection) {
        JWisHistory.currentSection = currentSection;
    }

    public static WisChapter getCurrentChapter() {
        return currentChapter;
    }

    public static void setCurrentChapter(WisChapter currentChapter) {
        JWisHistory.currentChapter = currentChapter;
    }

    public static WisItem getCurrentItem() {
        return currentItem;
    }

    public static void setCurrentItem(WisItem currentItem) {
        JWisHistory.currentItem = currentItem;
    }

    public static WisItemElement getCurrentElement() {
        return currentElement;
    }

    public static void setCurrentElement(WisItemElement currentElement) {
        JWisHistory.currentElement = currentElement;
    }

    public static WisSubElement getCurrentSubelement() {
        return currentSubelement;
    }

    public static void setCurrentSubelement(WisSubElement currentSubelement) {
        JWisHistory.currentSubelement = currentSubelement;
    }
}
