package ru.kjd.jwis.core;

import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

public class WisImgStreamHandler extends URLStreamHandler {
    private ResourceManager resourceManager;
    private WisHierarchy hierarchy;

    Logger log = Logger.getLogger(WisImgStreamHandler.class.getName());

    public WisImgStreamHandler(ResourceManager resourceManager, WisHierarchy hierarchy) {
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WisImgConnection(u);
    }

    private class WisImgConnection extends URLConnection {
        String imgName;

        @Override
        public void connect() throws IOException {
        }

        public WisImgConnection(URL u) {
            super(u);
            log.info("URL: " + url.toString());
            imgName = u.getHost();
        }

        public InputStream getInputStream() throws IOException {
            return resourceManager.getImgInputStream(hierarchy, imgName);
        }
    }
}
