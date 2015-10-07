package ru.kjd.jwis.core.utils;

import net.sf.jcgm.core.CGM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveScanner {
    private static Logger log = Logger.getLogger(ArchiveScanner.class.getName());

    public static List<String> scanArchive(File archive, String regex){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        InputStream is;
        try {
            is = new FileInputStream(archive);

            ZipInputStream zis = new ZipInputStream(is);

            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                Matcher matcher = pattern.matcher(ze.getName());
                if ( matcher.matches() )
                    list.add(ze.getName());

                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getContent(File zipFile, String entryName) {
        try {
            ZipInputStream zis = findInArchive(zipFile, entryName);

            InputStreamReader isr = new InputStreamReader(zis, "windows-1251");
            BufferedReader brin = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String part;
            while( (part = brin.readLine()) != null ) {
                if ( part.contains("img") || part.contains("IMG")){
                    System.out.println(part);
                }
                sb.append(part);
            }

            return sb.toString();
        } catch ( IOException ioex ) {
            return ioex.getMessage();
        }
    }

    public static ZipInputStream findInArchive(File zipFile, String name) throws IOException {
        FileInputStream is = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(is);

        ZipEntry ze = zis.getNextEntry();
        while( ze != null )
        {
            if ( ze.getName().equalsIgnoreCase(name) ){
                return zis;
            }
            ze = zis.getNextEntry();
        }

        throw new FileNotFoundException("Missing file " + name + " in archive " + zipFile.getName());
    }

    public static void unpackDocArchive(File zipFile, String imgRoot) throws IOException {
        makeDir(imgRoot);

        FileInputStream is = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(is);

        ZipEntry ze = zis.getNextEntry();
        while( ze != null )
        {
            extractDoc(zis, Paths.get(imgRoot, ze.getName()).toFile());
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    private static void makeDir(String imgRoot) {
        File dir = new File(imgRoot);
        if ( !dir.isDirectory() )
            dir.mkdirs();
    }

    private static void extractDoc(ZipInputStream zis, File outFile) throws IOException {
        InputStreamReader isr = new InputStreamReader(zis, "windows-1251");
        FileOutputStream fos = new FileOutputStream(outFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        BufferedReader brin = new BufferedReader(isr);

        String part;
        while( (part = brin.readLine()) != null ) {
            bw.write(part);
        }
        fos.close();
    }

    public static void unpackImgArchive(File imageArchive, String imgRoot) throws IOException {
        makeDir(imgRoot);
        FileInputStream is = new FileInputStream(imageArchive);
        ZipInputStream zis = new ZipInputStream(is);

        ZipEntry ze = zis.getNextEntry();
        while( ze != null )
        {
            String origName = ze.getName();
            String newName = origName.substring(0, origName.indexOf('.')).concat(".png");
            extractImg(zis, Paths.get(imgRoot, newName).toFile());
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    private static void extractImg(ZipInputStream zis, File file) {

        BufferedImage image;
        try {
            image = ImageIO.read(zis);
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

    }
}
