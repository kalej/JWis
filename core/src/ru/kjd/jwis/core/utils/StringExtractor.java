package ru.kjd.jwis.core.utils;

public class StringExtractor {
    public static String extractYear(String yearFile) {
        int begin = yearFile.length() - "yyyyll.xml".length();
        return yearFile.substring(begin, begin + "yyyy".length());
    }

    public static String extractLanguage(String yearFile){
        int begin = yearFile.length() - "ll.xml".length();
        return yearFile.substring(begin, begin + "ll".length());
    }

    public static Integer extractImgArchiveId(String name) {
        return Integer.parseInt(name.substring("images".length(), name.indexOf('.')));
    }

    public static String extractImageName(String name) {
        return name.substring(0, name.indexOf('.'));
    }

    public static Integer extractArchiveId(String name) {
        return Integer.parseInt(name.substring("ll".length(), name.indexOf('.')));
    }

    public static Integer extractDocId(String name) {
        return Integer.parseInt(name.substring("doc".length(), name.indexOf('.')));
    }

}
