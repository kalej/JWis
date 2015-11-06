package ru.kjd.jwis.core.enums;

import ru.kjd.jwis.core.xml.WisItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public enum WisItemType {
    TECH_DATA("0301", "00.png"),
    SPECIAL_TOOLS("1101", "01.png"),
    TECH_DESC("0902", "02.png"),
    TROUBLESHOOT("0601", "03.png"),
    FAULT_CODES("0602", "04.png"),
    SYMPTOMS("0603", "05.png"),
    REPLACEMENT("0801", "06.png"),
    LOCATION("0401", "07.png"),
    ELECTRICS("1001", "08.png"),
    BULLETINS("0500", "09.png"),
    SERVICE("0701", "10.png");

    private String value;
    private String picture;

    WisItemType(String value, String picture){
        this.value = value;
        this.picture = picture;
    }

    public String getPicture() {
        return Paths.get("tabs", picture).toString();
    }

    public static WisItemType byValue(String value){
        for( WisItemType itemType : WisItemType.values() )
            if ( itemType.value.equals(value) )
                return itemType;
        return null;
    }
}
