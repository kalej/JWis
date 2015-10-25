package ru.kjd.jwis.core.enums;

import ru.kjd.jwis.core.xml.WisItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public enum WisItemType {
    TECH_DATA(3, "00.png"),
    SPECIAL_TOOLS(11, "01.png"),
    TECH_DESC(9, "02.png"),
    TROUBLESHOOT(6, "03.png"),
    FAULT_CODES(0, "04.png"),
    SYMPTOMS(1, "05.png"),
    REPLACEMENT(8, "06.png"),
    LOCATION(4, "07.png"),
    ELECTRICS(10, "08.png"),
    BULLETINS(5, "09.png"),
    SERVICE(7, "10.png");

    private int value;
    private String picture;

    WisItemType(int value, String picture){
        this.value = value;
        this.picture = picture;
    }

    public static WisItemType valueOf(int value){
        for ( WisItemType type : values())
            if ( type.value == value )
                return type;

        return null;
    }

    public String getPicture() {
        return Paths.get("tabs", picture).toString();
    }


}
