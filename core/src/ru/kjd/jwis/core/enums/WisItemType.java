package ru.kjd.jwis.core.enums;

/**
 * Created by anonymous on 10/5/15.
 */
public enum WisItemType {
    WIRING(10),
    REPLACEMENT(8),
    TECHNICAL(3),
    DESCRIPTION(9),
    TROUBLESHOOTING(6),
    BULLETINS(5),
    LOCATION(4),
    SERVICE(7),
    TOOLS(11),
    UNKNOWN(-1);

    private int value;

    private WisItemType(int value){
        this.value = value;
    }

    public static WisItemType valueOf(int value){
        for ( WisItemType type : values())
            if ( type.value == value )
                return type;

        return UNKNOWN;
    }
}
