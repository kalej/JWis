package ru.kjd.jwis.jwisfx.urlhandlers;

import javafx.concurrent.Task;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.jwisfx.JWisHistory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class WisImgStreamHandler extends URLStreamHandler {
    static Logger log = Logger.getLogger(WisImgStreamHandler.class.getName());

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
            if (file != null && !file.isEmpty()) {
                long millis = System.currentTimeMillis();
                InputStream is = ResourceManager.getInstance().getImgInputStream(JWisHistory.getCurrentHierarchy(), url.getHost());
                log.info("done in " + (System.currentTimeMillis() - millis) + "ms" );
                return is;
            }
            else {
                String content = "<html><body><img src=\"" + url.toString() + "/show\"/></body></html>";
                log.info(content);
                return new ByteArrayInputStream(content.getBytes());
            }
        }
    }
}
