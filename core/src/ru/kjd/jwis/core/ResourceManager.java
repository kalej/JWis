package ru.kjd.jwis.core;

import javafx.scene.image.Image;
import javafx.util.Pair;
import net.sf.jcgm.core.CGM;
import net.sf.jcgm.core.CGMDisplay;
import ru.kjd.jwis.core.utils.DirectoryScanner;
import ru.kjd.jwis.core.utils.StringExtractor;
import ru.kjd.jwis.core.xml.*;

import javax.imageio.ImageIO;
import javax.xml.bind.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ResourceManager {
    private static ResourceManager instance = null;
    private static final String MODEL_REGEX = "^9-?[0-9]{1,3}x?( \\([0-9]{3,4}\\))?";
    private static final String YEAR_REGEX = "[0-9]{4}";
    private static final String XML_EXT_REGEX = "\\.xml$";
    private static final String IMAGE_REGEX = "^images[0-9]{1,2}\\.zip";
    private static Logger log = Logger.getLogger(ResourceManager.class.getName());
    private SortedMap<String, Pair<String, String>> imageToArchive;
    private SortedMap<String, List<String>> modelToXmls = new TreeMap<>();
    private WisProperties properties;

    public static ResourceManager getInstance() {
        if ( instance == null )
            instance = new ResourceManager(new WisProperties());
        return instance;
    }

    public ResourceManager(WisProperties properties) {
        this.properties = properties;
        WisPaths.setLanguage(properties.getLanguage());
        findResources();
    }

    public Set<String> getModelList() {
        return modelToXmls.keySet();
    }

    public List<String> getXmls(String model) {
        return modelToXmls.get(model);
    }

    private String getYearRegex(String model) {
        return model
                .replace("(", "\\(")
                .replace(")", "\\)")
                .concat(YEAR_REGEX)
                .concat(properties.getLanguage().getShortName())
                .concat(XML_EXT_REGEX);
    }

    private void findResources() {
        List<String> models = DirectoryScanner.getFilenames(WisPaths.ROOT, MODEL_REGEX + "$");
        for (String model : models) {
            String yearRegex = getYearRegex(model);
            List<String> years = DirectoryScanner.getFilenames(WisPaths.getModelRoot(model), yearRegex);
            if (years.size() > 0) {
                Collections.sort(years);
                modelToXmls.put(model, years);
            }
        }
    }

    private SortedMap<String, Pair<String, String>> scanImgArchives(String model) {
        SortedMap<String, Pair<String, String>> map = new TreeMap<>();
        File[] modelImgFiles = DirectoryScanner.getFiles(WisPaths.getModelRoot(model), IMAGE_REGEX);
        for (File file : modelImgFiles) {
            try {
                ZipFile zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    String entryname = entries.nextElement().getName();
                    Pair<String, String> extArch = new Pair(entryname.substring(entryname.indexOf('.')), file.getName());
                    map.put(entryname.substring(0, entryname.indexOf('.')), extArch);
                }
            } catch (ZipException e) {
                log.info(e.getMessage());
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return map;
    }

    public WisHierarchy loadXMLHierarchy(String model, String year) throws IOException, JAXBException {
        String xmlPath = WisPaths.getXMLPath(model, year);
        InputStream is = new FileInputStream(new File(xmlPath));
        InputStreamReader isr = new InputStreamReader(is, properties.getCharset());
        BufferedReader brin = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String part;
        while ((part = brin.readLine()) != null) {
            sb.append(part.trim());
        }
        String unescaped = sb.toString().replaceAll("&nbsp;", "\u00A0"); //StringEscapeUtils.unescapeHtml(sb.toString());

        JAXBContext jaxbContext = JAXBContext.newInstance(WisHierarchy.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Reader reader = new StringReader(unescaped);
        unmarshaller.setEventHandler(new WisXmlEventHandler());

        WisHierarchy hierarchy = (WisHierarchy) unmarshaller.unmarshal(reader);
        hierarchy.setReverseLinks();
        imageToArchive = scanImgArchives(model);
        return hierarchy;
    }

    public InputStream getWorkImgInputStream(String imgfile) throws FileNotFoundException {
        File file = new File(WisPaths.getWorkImgPath(imgfile));
        return new FileInputStream(file);
    }

    public Image getImagePreview(WisHierarchy hierarchy, String substring, double width, double height) throws IOException {
        return new Image(getImgInputStream(hierarchy, substring), width, height, true, false);
    }

    public Image getImage(WisHierarchy hierarchy, String imgName) throws IOException {
        return new Image(getImgInputStream(hierarchy, imgName));
    }

    public InputStream getLinkInputStream(WisHierarchy hierarchy, int linkId) throws IOException {
        return getDocInputStream(hierarchy, findLinkedDoc(hierarchy, linkId));
    }

    private int findLinkedDoc(WisHierarchy hierarchy, int linkId) throws IOException {
        for (WisSection section : hierarchy.getSections())
            for (WisChapter chapter : section.getChapters())
                for (WisItem item : chapter.getItems())
                    for (WisItemElement itemElement : item.getElements())
                        if (itemElement.getId() == linkId)
                            itemElement.getDocId();
        return 0;
    }

    public InputStream getDocInputStream(WisHierarchy hierarchy, int docId) throws IOException {
        File file = new File(WisPaths.getZipFile(hierarchy, docId));
        ZipFile zipFile = new ZipFile(file);
        ZipEntry zipEntry = zipFile.getEntry(WisPaths.getDocFileName(docId));
        return zipFile.getInputStream(zipEntry);
    }

    public InputStream getImgInputStream(WisHierarchy hierarchy, String imgName) throws IOException {
        String fileName;
        if (imgName.charAt(0) == 'i') {
            fileName = imgName.substring(1);
        } else {
            fileName = imgName;
        }

        Pair<String, String> extArch = imageToArchive.get(fileName);

        if (extArch == null) {
            log.info("Image " + fileName + " not found");
            return null;
        }

        log.info("Image " + fileName + extArch.getKey() + " found in " + extArch.getValue());


        File archive = new File(WisPaths.getArchive(hierarchy, extArch.getValue()));
        ZipFile zipFile = new ZipFile(archive);
        ZipEntry entry = zipFile.getEntry(fileName + extArch.getKey());

        if (extArch.getKey().endsWith("cgm"))
            return getConvertedFileStream(zipFile.getInputStream(entry));
        else
            return zipFile.getInputStream(entry);
    }

    private BufferedImage convertImage(CGM cgm){
        CGMDisplay cgmDisplay = new CGMDisplay(cgm);
        Dimension cgmDimension = cgm.getSize();
        int cgmWidth = (int)Math.round(Math.floor(cgmDimension.getWidth()));
        int cgmHeight = (int)Math.round(Math.floor(cgmDimension.getHeight()));

        BufferedImage image = new BufferedImage(cgmWidth, cgmHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.getGraphics();
        cgmDisplay.scale(graphics, cgmWidth, cgmHeight);
        cgmDisplay.paint(graphics);

        return image;
    }

    private void unpackToFile(InputStream inputStream, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);

        int len;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
    }



    private InputStream getConvertedFileStream(InputStream inputStream) throws IOException {
        //File tmpCgm = File.createTempFile("img", ".cgm");
        //unpackToFile(inputStream, tmpCgm);

        //CGM cgm = new CGM(tmpCgm);
        CGM cgm = new CGM(inputStream);
        BufferedImage image = convertImage(cgm);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
        //File tmpPng = File.createTempFile("img", ".png");
        //ImageIO.write(image, "png", tmpPng);

        //log.info("Unpacked to " + tmpCgm.getName());
        //log.info("Converted to " + tmpPng.getName());
        //return new FileInputStream(tmpPng);
    }

    public List<String> getYears(String model) {
        List<String> xmls = getXmls(model);
        List<String> result = new ArrayList<>();
        for( String xml : xmls ){
            result.add(StringExtractor.extractYear(xml));
        }
        return result;
    }

    private static class WisXmlEventHandler implements ValidationEventHandler {
        @Override
        public boolean handleEvent(ValidationEvent event) {
            log.info(event.getMessage());
            return false;
        }
    }
}
