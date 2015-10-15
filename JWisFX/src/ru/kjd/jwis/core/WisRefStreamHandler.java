package ru.kjd.jwis.core;

import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.jwisfx.gui.WisItemTabPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

/**
 * Created by anonymous on 10/14/15.
 */
public class WisRefStreamHandler extends URLStreamHandler {
    private static Logger log = Logger.getLogger(WisRefStreamHandler.class.getName());
    ResourceManager resourceManager;
    WisHierarchy wisHierarchy;
    WisItemTabPane wisItemTabPane;

    public WisRefStreamHandler(ResourceManager resourceManager, WisHierarchy wisHierarchy, WisItemTabPane wisItemTabPane) {
        this.resourceManager = resourceManager;
        this.wisHierarchy = wisHierarchy;
        this.wisItemTabPane = wisItemTabPane;
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WisRefConnection(u);
    }

    private class WisRefConnection extends URLConnection {
        URL u;

        public WisRefConnection(URL u) {
            super(u);
            this.u = u;
        }

        @Override
        public void connect() throws IOException {

        }

        public InputStream getInputStream() throws IOException {
            log.info(u.toString());

            String file = u.getFile();
            String host = u.getHost();

            if (file == null || file.isEmpty()) {
                int linkId = Integer.parseInt(host.substring(1));
                int dest = wisItemTabPane.getDestId(linkId);
                int docId = wisItemTabPane.getDocId(dest);
                InputStream is = resourceManager.getDocInputStream(wisHierarchy, docId);
                return is;
            } else if (file.charAt(0) == 'i')
                return resourceManager.getImgInputStream(wisHierarchy, file.substring(1));
            else
                return resourceManager.getWorkImgInputStream(file);
        }
    }
}
