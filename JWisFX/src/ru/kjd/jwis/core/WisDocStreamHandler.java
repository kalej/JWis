package ru.kjd.jwis.core;

import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

public class WisDocStreamHandler extends URLStreamHandler {
    Logger log = Logger.getLogger(WisDocStreamHandler.class.getName());

    private ResourceManager resourceManager;
    private WisHierarchy hierarchy;

    public WisDocStreamHandler(ResourceManager resourceManager, WisHierarchy hierarchy) {
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WisDocConnection(u);
    }

    private class WisDocConnection extends URLConnection {
        URL url;

        @Override
        public void connect() throws IOException {
        }

        public WisDocConnection(URL url) {
            super(url);
            this.url = url;
            log.info("URL: " + url.toString());
        }

        public InputStream getInputStream() throws IOException {
            String host = url.getHost();
            String file = url.getFile();

            if ( file != null && !file.isEmpty() ){
                if ( file.charAt(0) == 'i' )
                    return resourceManager.getImgInputStream(hierarchy, file.substring(1));
                else
                    return resourceManager.getWorkImgInputStream(file);
            }
            else
            {
                return resourceManager.getDocInputStream(hierarchy, Integer.parseInt(host));
            }

        }
    }
}
