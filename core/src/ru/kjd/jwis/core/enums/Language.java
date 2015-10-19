package ru.kjd.jwis.core.enums;

public enum Language {
    GERMANY("de"),
    SPAIN("es"),
    FINLAND("fi"),
    FRANCE("fr"),
    ENGLAND("gb"),
    ITALY("it"),
    JAPAN("jp"),
    KOREA("kr"),
    NETHERLANDS("nl"),
    RUSSIA("ru"),
    SEYCHELLES("sc"),
    SWEDEN("se"),
    TURKEY("tc"),
    THAILAND("th"),
    USA("us");

    private final String shortName;

    Language(String shortName){
        this.shortName = shortName;
    }

    public static Language valueOfSName(String s){
        for( Language l : Language.values() ){
            if ( l.shortName.equals(s))
                return l;
        }

        return null;
    }

    public String getShortName() {
        return shortName;
    }
}
