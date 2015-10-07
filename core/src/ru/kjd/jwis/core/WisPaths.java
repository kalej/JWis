package ru.kjd.jwis.core;

import ru.kjd.jwis.core.enums.Language;
import ru.kjd.jwis.core.xml.WisHierarchy;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WisPaths {
    public static String ROOT;
    public static final String XML_EXT = "xml";
    public static final String ZIP_EXT = "zip";
    public static final String GSON_EXT = "gson";
    private static final String DOC_PATH = "doc";
    private static final String IMG_PATH = "img";
    private static final String WORK_PATH = "work";

    static {
        String OS = System.getProperty("os.name").toLowerCase();
        if ( OS.indexOf("droid") > 0 ){
            ROOT = "/sdcard/wis";
        } else {
            ROOT = "wis";
        }
    }

    public static String getResourceRoot(){
        return ROOT;
    }
    public static String getModelRoot(WisHierarchy hierarchy){
        return getModelRoot(hierarchy.getCarModel());
    }

    public static String getModelRoot(String model){
        return Paths.get(ROOT, model).toString();
    }

    public static String getDocArchive(WisHierarchy hierarchy, Language language, int docId ){
        return Paths.get(getModelRoot(hierarchy), language.getShortName() + docId/1000 + ".zip").toString();
    }

    public static String getDocRoot(WisHierarchy hierarchy){
        return Paths.get(getModelRoot(hierarchy), DOC_PATH).toString();
    }

    public static String getDocRoot(String model){
        return Paths.get(getModelRoot(model), DOC_PATH).toString();
    }

    public static String getImgRoot(WisHierarchy hierarchy){
        return Paths.get(getModelRoot(hierarchy), IMG_PATH).toString();
    }

    public static String getImgRoot(String car){
        return Paths.get(getModelRoot(car), IMG_PATH).toString();
    }

    public static String getXMLPath(WisHierarchy hierarchy){
        return getXMLPath(hierarchy.getCarModel(), Integer.toString(hierarchy.getModelYear()));
    }

    public static String getGsonPath(WisHierarchy hierarchy){
        return Paths.get(getModelRoot(hierarchy), hierarchy.getModelYear() + "." + GSON_EXT).toString();
    }


    public static String getDocFile(WisHierarchy car, int docId) {
        return Paths.get(getDocRoot(car), "doc" + docId + ".htm").toString();
    }

    public static String getImgFile(WisHierarchy car, String imgName){
        return Paths.get(getImgRoot(car), imgName).toString();
    }

    public static String getXMLPath(String model, String year) {
        return Paths.get(getModelRoot(model), year + (year.endsWith(XML_EXT)?"":"." + XML_EXT)).toString();
    }

    public static String getWorkImgPath(String imgName){
        return Paths.get(getWorkPath(), imgName).toString();
    }

    public static String getWorkPath() {
        return Paths.get(getResourceRoot(), WORK_PATH).toString();
    }
}
