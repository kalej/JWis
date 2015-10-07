package ru.kjd.jwis.core.test;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.WisPaths;
import ru.kjd.jwis.core.WisProperties;
import ru.kjd.jwis.core.utils.StringExtractor;
import ru.kjd.jwis.core.xml.*;

import java.util.Set;

public class ResourceTest {
    private static Logger log = Logger.getLogger(ResourceTest.class.getName());


    @Test
    public void testFindResources() throws IOException, JAXBException {
        WisProperties properties = new WisProperties();
        ResourceManager resourceManager = new ResourceManager(properties);

        Map<String, Set<String>> numToTitles = new HashMap<>();
        for (String model : resourceManager.getModelList()) {
            for (String year : resourceManager.getYears(model)) {
                String xmlPath = WisPaths.getXMLPath(model, year);
                WisHierarchy hierarchy = resourceManager.loadXMLHierarchy(xmlPath);

                for (WisSection section : hierarchy.getSections()) {
                    for ( WisChapter chapter : section.getChapters() ) {
                        Set<String> titles = numToTitles.get(chapter.getNum());
                        if (titles == null) {
                            titles = new HashSet<>();
                            numToTitles.put(chapter.getNum(), titles);
                        }
                        titles.add(chapter.getName());
                    }
                }
            }
        }

        for (String num : numToTitles.keySet()) {
            System.out.println(num + ":");
            for (String title : numToTitles.get(num))
                System.out.println(title + "; ");
        }
    }
}
