package ru.kjd.jwis.core.enums;

import ru.kjd.jwis.core.xml.WisItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public enum WisItemType {
    WIRING(10, "schematic.png"),
    REPLACEMENT(8, "repair.png"),
    TECHNICAL(3, "technical.png"),
    DESCRIPTION(9, "description.png"),
    TROUBLESHOOTING(6, "troubleshoot.png"),
    BULLETINS(5, "bulletin.png"),
    LOCATION(4, "location.png"),
    SERVICE(7, "service.png"),
    TOOLS(11, "service.png");

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
