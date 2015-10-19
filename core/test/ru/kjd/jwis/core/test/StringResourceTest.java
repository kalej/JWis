package ru.kjd.jwis.core.test;

import javafx.util.Pair;
import org.junit.Test;
import ru.kjd.jwis.core.enums.Language;

import java.io.*;
import java.util.*;

/**
 * Created by anonymous on 10/19/15.
 */
public class StringResourceTest {
    private static final String BASELANG="GB";

    private class ResourceString{
        private String caption;
        private String tooltip;
        private String propName;

        public ResourceString(String caption, String tooltip) {
            this.caption = caption;
            this.tooltip = tooltip;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getTooltip() {
            return tooltip;
        }

        public void setTooltip(String tooltip) {
            this.tooltip = tooltip;
        }

        public String getPropName(){
            String[] parts = caption.toLowerCase().trim().split(" ");
            StringBuilder sb = new StringBuilder("string.");

            for( int i = 0; i < parts.length - 1; i++ ){
                sb.append(parts[i]).append('.');
            }
            sb.append(parts[parts.length-1]);
            return sb.toString();
        }
    }

    @Test
    public void stringResourceConversion() throws IOException {
        Class c = StringResourceTest.class;
        ClassLoader cl = c.getClassLoader();
        InputStream is = cl.getResourceAsStream("strings/resourceGB_str.txt");
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis, "UTF-16");

        SortedMap<Integer, ResourceString> baseMap = loadTxt(isr);
        for( Language language : Language.values() ){
            is = cl.getResourceAsStream("strings/resource" + language.getShortName().toUpperCase() + "_str.txt");
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis, "UTF-16");

            SortedMap<Integer, ResourceString> stringMap = loadTxt(isr);

            File langFile = new File(language.getShortName() + ".string.properties");
            FileOutputStream fos = new FileOutputStream(langFile);
            PrintStream ps = new PrintStream(fos);

            Set<String> usedPropNames = new HashSet<>();
            for(Integer id : baseMap.keySet() ) {
                String propName = baseMap.get(id).getPropName();
                if ( usedPropNames.contains(propName) ){
                    char ch = 'a';
                    while ( usedPropNames.contains(propName + '.' + String.valueOf(ch)) ) ch++;
                    propName += '.' + String.valueOf(ch);
                }
                usedPropNames.add(propName);

                String propVal = stringMap.get(id).getCaption();
                String propTip = stringMap.get(id).getTooltip();

                ps.print(propName);
                ps.print("=");
                ps.println(propVal);

                if ( !propTip.isEmpty() ){
                    ps.print(propName + ".tip");
                    ps.print("=");
                    ps.println(propTip);
                }
            }

            fos.flush();
            fos.close();
        }
    }

    private String propNameToConstName(String propName){
        return propName.toUpperCase().replaceAll("\\.", "_");
    }

    @Test
    public void stringResourceClassGen() throws IOException {
        Class c = StringResourceTest.class;
        ClassLoader cl = c.getClassLoader();
        InputStream is = cl.getResourceAsStream("strings/resourceGB_str.txt");
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis, "UTF-16");

        SortedMap<Integer, ResourceString> baseMap = loadTxt(isr);
        for( Language language : Language.values() ){
            is = cl.getResourceAsStream("strings/resource" + language.getShortName().toUpperCase() + "_str.txt");
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis, "UTF-16");

            SortedMap<Integer, ResourceString> stringMap = loadTxt(isr);

            File langFile = new File("StringProp.txt");
            FileOutputStream fos = new FileOutputStream(langFile);
            PrintStream ps = new PrintStream(fos);

            Set<String> usedPropNames = new HashSet<>();
            for(Integer id : baseMap.keySet() ) {
                String propName = baseMap.get(id).getPropName();
                if ( usedPropNames.contains(propName) ){
                    char ch = 'a';
                    while ( usedPropNames.contains(propName + '.' + String.valueOf(ch)) ) ch++;
                    propName += '.' + String.valueOf(ch);
                }
                usedPropNames.add(propName);

                String propVal = stringMap.get(id).getCaption();
                String propTip = stringMap.get(id).getTooltip();

                ps.print("private static final String ");
                ps.print(propNameToConstName(propName));
                ps.print(" = \"");
                ps.print(propName);
                ps.println("\";");

                if ( !propTip.isEmpty() ){
                    ps.print("private static final String ");
                    ps.print(propNameToConstName(propName + ".tip"));
                    ps.print(" = \"");
                    ps.print(propName + ".tip");
                    ps.println("\";");
                }
            }

            fos.flush();
            fos.close();
        }
    }

    private SortedMap<Integer, ResourceString> loadTxt(Reader input) throws IOException {
        LineNumberReader reader = new LineNumberReader(input);

        SortedMap<Integer, ResourceString> result = new TreeMap<>();
        String line;
        ResourceString latest = null;

        while((line = reader.readLine())!=null){
            if ( line.trim().isEmpty() )
                continue;

            String[] substr = line.split(" ");
            try {
                int id = Integer.valueOf(substr[0]);
                StringBuilder sb = new StringBuilder();
                for( int i = 1; i < substr.length; i++ )
                    sb.append(substr[i]).append(' ');

                latest = new ResourceString(sb.toString().trim(), "");
                result.put(id, latest);
            } catch (NumberFormatException nfe){
                latest.setTooltip(latest.getTooltip().concat(line));
            }
        }
        return result;
    }
}
