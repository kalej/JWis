package ru.kjd.jwis.core;

import javafx.scene.image.Image;
import ru.kjd.jwis.core.utils.ArchiveScanner;
import ru.kjd.jwis.core.utils.DirectoryScanner;
import ru.kjd.jwis.core.xml.WisHierarchy;

import javax.imageio.ImageIO;
import javax.xml.bind.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by anonymous on 10/1/15.
 */
public class ResourceManager {
    private static final String MODEL_REGEX = "^9-?[0-9]{1,3}x?( \\([0-9]{3,4}\\))?$";
    private static final String YEAR_REGEX = "[0-9]{4}\\.xml$";

    private SortedMap<String, List<String>> modelsMaps = new TreeMap<>();

    private static Logger log = Logger.getLogger(ResourceManager.class.getName());
    private WisProperties properties;

    public ResourceManager(WisProperties properties) {
        this.properties = properties;

        findResources();
    }

    public Set<String> getModelList(){
        return modelsMaps.keySet();
    }

    public List<String> getYears(String model){
        return modelsMaps.get(model);
    }

    public void findResources(){
        List<String> models = DirectoryScanner.getFilenames(WisPaths.ROOT, MODEL_REGEX);
        for( String model : models ){
            List<String> years = DirectoryScanner.getFilenames(WisPaths.getModelRoot(model), YEAR_REGEX);
            if ( years.size() > 0 ){
                Collections.sort(years);
                modelsMaps.put(model, years);
            }
        }
    }

    private void unpackImages() {
        for (String model : getModelList() ) {
            File[] imageArchives = scanImgArchives(model);

            for ( File imageArchive : imageArchives ){
                try {
                    ArchiveScanner.unpackImgArchive(imageArchive, WisPaths.getImgRoot(model));
                    imageArchive.delete();
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        }
    }

    public void unpackDocs(){
        for (String model : getModelList() ){
            File[] docArchires = scanDocArchives(model);
            for ( File docArchive : docArchires ){
                try {
                    ArchiveScanner.unpackDocArchive(docArchive, WisPaths.getDocRoot(model));
                    docArchive.delete();
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        }
    }

    //public static Set<Language> getAvailableLanguages() {
    //    return availableLanguages;
    //}

    //public static void setSelectedLanguage(Language selectedLanguage) {
    //    ResourceManager.selectedLanguage = selectedLanguage;
    //}

    public File[] scanDocArchives(String model){
        String pattStr = "^" + properties.getLanguage().getShortName() + "[0-9]{1,2}\\.zip";
        return DirectoryScanner.getFiles(WisPaths.getModelRoot(model), pattStr);
    }

    public File[] scanImgArchives(String model){
        File[] modelDocFiles = DirectoryScanner.getFiles(WisPaths.getModelRoot(model), "^images[0-9]{1,2}\\.zip" );
        return modelDocFiles;
    }

    private WisHierarchy loadXMLHierarchy(String model, String year) throws IOException, JAXBException {
        String fullName = WisPaths.getXMLPath(model, year);
        return loadXMLHierarchy(fullName);
    }

    public WisHierarchy loadXMLHierarchy(String xmlPath) throws IOException, JAXBException {
        InputStream is = new FileInputStream(new File(xmlPath));
        InputStreamReader isr = new InputStreamReader(is, properties.getCharset());
        BufferedReader brin = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String part;
        while( (part = brin.readLine()) != null ) {
            sb.append(part.trim());
        }
        String unescaped = sb.toString().replaceAll("&nbsp;", "\u00A0"); //StringEscapeUtils.unescapeHtml(sb.toString());

        JAXBContext jaxbContext = JAXBContext.newInstance(WisHierarchy.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Reader reader = new StringReader(unescaped);
        unmarshaller.setEventHandler(new WisXmlEventHandler());

        WisHierarchy hierarchy = (WisHierarchy) unmarshaller.unmarshal(reader);
        hierarchy.setReverseLinks();
        return hierarchy;
    }

    public InputStream getWorkImgInputStream(String imgfile) throws FileNotFoundException {
        File file = new File(WisPaths.getWorkImgPath(imgfile));
        return new FileInputStream(file);
    }

    public Image getImagePreview(WisHierarchy hierarchy, String substring, double width, double height) throws FileNotFoundException {
        return new Image(getImgInputStream(hierarchy, substring), width, height, true, false);
    }

    public Image getImage(WisHierarchy hierarchy, String imgName) throws FileNotFoundException {
        return new Image(getImgInputStream(hierarchy, imgName));
    }

    private static class WisXmlEventHandler implements ValidationEventHandler {
        @Override
        public boolean handleEvent(ValidationEvent event) {
            log.info(event.getMessage());
            return false;
        }
    }

    public FileInputStream getDocInputStream(WisHierarchy hierarchy, int docId) throws FileNotFoundException {
        File docFile = new File(WisPaths.getDocFile(hierarchy, docId));
        return new FileInputStream(docFile);
    }

    public FileInputStream getImgInputStream(WisHierarchy hierarchy, String imgName) throws FileNotFoundException {
        if ( imgName.charAt(0) == 'i' ) {
            String fileName = imgName.substring(1);
            File pngfile = new File(WisPaths.getImgFile(hierarchy, fileName.concat(".png")));
            try {
                return new FileInputStream(pngfile);
            } catch (Exception e){
                File cgmfile = new File(WisPaths.getImgFile(hierarchy, fileName.concat(".cgm")));
                FileInputStream cgmis = new FileInputStream(cgmfile);

                if ( cgmis == null )
                    return null;

                try {
                    BufferedImage image = ImageIO.read(cgmis);
                    ImageIO.write(image, "png", pngfile);
                    cgmfile.delete();
                    return new FileInputStream(pngfile);
                } catch (IOException ex) {
                    log.info(ex.getMessage());
                }
            }
        }
        File imgFile = new File(WisPaths.getImgFile(hierarchy, imgName));
        return new FileInputStream(imgFile);
    }
}
