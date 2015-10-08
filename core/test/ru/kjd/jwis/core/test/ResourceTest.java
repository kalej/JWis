package ru.kjd.jwis.core.test;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.WisPaths;
import ru.kjd.jwis.core.WisProperties;
import ru.kjd.jwis.core.xml.*;

import java.util.Set;

public class ResourceTest {
    private static Logger log = Logger.getLogger(ResourceTest.class.getName());


    @Test
    public void testFindResources() throws IOException, JAXBException {
        WisProperties properties = new WisProperties();
        ResourceManager resourceManager = new ResourceManager(properties);


    }
}
