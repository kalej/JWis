package ru.kjd.jwis.core;

import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

public class WisImgStreamHandler extends URLStreamHandler {
    static Logger log = Logger.getLogger(WisImgStreamHandler.class.getName());
    private ResourceManager resourceManager;
    private WisHierarchy hierarchy;

    public WisImgStreamHandler(ResourceManager resourceManager, WisHierarchy hierarchy) {
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WisImgConnection(u);
    }

    private class WisImgConnection extends URLConnection {
        URL url;

        public WisImgConnection(URL u) {
            super(u);
            this.url = u;
        }

        @Override
        public void connect() throws IOException {
        }

        public InputStream getInputStream() throws IOException {
            log.info("URL: " + url.toString());

            String file = url.getFile();
            if (file != null && !file.isEmpty())
                return resourceManager.getImgInputStream(hierarchy, url.getHost());
            else {
                String content = "<html><body><img src=\"" + url.toString() + "/show\"/></body></html>";
                log.info(content);
                return new ByteArrayInputStream(content.getBytes());
            }
        }
    }
}
