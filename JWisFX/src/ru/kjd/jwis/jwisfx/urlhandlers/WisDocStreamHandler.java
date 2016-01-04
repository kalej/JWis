package ru.kjd.jwis.jwisfx.urlhandlers;

import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.jwisfx.JWisHistory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Logger;

public class WisDocStreamHandler extends URLStreamHandler {
    static Logger log = Logger.getLogger(WisDocStreamHandler.class.getName());

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WisDocConnection(u);
    }

    private class WisDocConnection extends URLConnection {
        URL url;

        public WisDocConnection(URL url) {
            super(url);
            this.url = url;
            log.info("URL: " + url.toString());
        }

        @Override
        public void connect() throws IOException {
        }

        public InputStream getInputStream() throws IOException {
            String host = url.getHost();
            String file = url.getFile();

            if (file != null && !file.isEmpty()) {
                if (file.charAt(0) == 'i')
                    return ResourceManager.getInstance().getImgInputStream(JWisHistory.getCurrentHierarchy(), file.substring(1));
                else
                    return ResourceManager.getInstance().getWorkImgInputStream(file);
            } else {
                if (host.charAt(0) == 'l')
                    return ResourceManager.getInstance().getLinkInputStream(JWisHistory.getCurrentHierarchy(), Integer.parseInt(host.substring(1)));
                else
                    return ResourceManager.getInstance().getDocInputStream(JWisHistory.getCurrentHierarchy(), Integer.parseInt(host));
            }

        }
    }
}
