package ru.kjd.jwis.core;

import ru.kjd.jwis.core.enums.Language;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Properties;

public class WisProperties {
    public static final String PROG_NAME = "JWis 0.1 beta";
    private static final String PROP_FILE_NAME = "wis.properties";
    private static final String PROP_LANGUAGE = "wis.resource.language";
    private static final String PROP_ENCODING = "wis.resource.encoding";

    private static Charset charset;
    private static Language language;

    static {

        try {
            FileInputStream fis = new FileInputStream(new File(Paths.get(WisPaths.getResourceRoot(), PROP_FILE_NAME).toString()));
            Properties properties = new Properties();
            properties.load(fis);

            charset = Charset.forName(properties.getProperty(PROP_ENCODING));
            language = Language.valueOfSName(properties.getProperty(PROP_LANGUAGE));
        } catch (IOException e) {
            charset = Charset.forName("windows-1251");
            language = Language.RUSSIA;
        }
    }

    public static Charset getCharset() {
        return charset;
    }

    public static Language getLanguage() {
        return language;
    }
}
