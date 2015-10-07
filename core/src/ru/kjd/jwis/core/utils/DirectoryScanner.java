package ru.kjd.jwis.core.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryScanner {
    public static File[] getFiles(String path, final String regex){
        File directory = new File(path);

        return directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Pattern patt = Pattern.compile(regex);
                Matcher matcher = patt.matcher(name);
                return matcher.matches();
            }
        });
    }

    public static List<String> getFilenames(String path, final String regex){
        File[] files = getFiles(path, regex);
        List<String> list = new ArrayList<>();
        for( File file : files )
            list.add(file.getName());

        return list;
    }
}
