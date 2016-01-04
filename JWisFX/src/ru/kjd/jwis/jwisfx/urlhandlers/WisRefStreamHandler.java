package ru.kjd.jwis.jwisfx.urlhandlers;

import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.jwisfx.JWisHistory;

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
                String dest = JWisHistory.getCurrentElement().getDestId(linkId);
                int docId = JWisHistory.getCurrentElement().getDocId(Integer.parseInt(dest));
                InputStream is = ResourceManager.getInstance().getDocInputStream(JWisHistory.getCurrentHierarchy(), docId);
                return is;
            } else if (file.charAt(0) == 'i')
                return ResourceManager.getInstance().getImgInputStream(JWisHistory.getCurrentHierarchy(), file.substring(1));
            else
                return ResourceManager.getInstance().getWorkImgInputStream(file);
        }
    }
}
