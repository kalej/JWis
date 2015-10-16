package ru.kjd.jwis.test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anonymous on 10/16/15.
 */
public class ImageSplitterTest {
    @Test
    public void splitImages() throws IOException {
        splitImage("wis_128", 11);
        splitImage("wis_130", 6);
        splitImage("wis_131", 22);
        splitImage("wis_144", 9);
        splitImage("wis_205", 10);
        splitImage("wis_206", 10);
    }

    public void splitImage(String name, int parts) throws IOException {
        Class c = ImageSplitterTest.class;
        ClassLoader cl = c.getClassLoader();

        InputStream is = cl.getResourceAsStream(name + ".bmp");

        BufferedImage fullImage = ImageIO.read(is);
        int height = fullImage.getHeight();
        int width = fullImage.getWidth()/parts;

        for( int i = 0; i < parts; i++ ){
            BufferedImage part = new BufferedImage(height, height, fullImage.getType());

            Graphics2D gr = part.createGraphics();
            gr.drawImage(fullImage, 0, 0, width, height, width*i, 0, width*(i+1), height, null );

            gr.dispose();

            ImageIO.write(part, "PNG", new File(name + height + "px_" + i + ".png"));
        }
    }
}
