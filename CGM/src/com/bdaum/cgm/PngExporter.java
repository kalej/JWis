package com.bdaum.cgm;/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  PngExporter
 *  com.bdaum.cgm.CgmPanel
 */
import com.bdaum.cgm.CgmPanel;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PngExporter {
    final JFileChooser fc = new JFileChooser();

    public void exportPNG(CgmPanel canvas) {
        if (this.fc.showSaveDialog((Component)canvas) != 0) {
            return;
        }
        File pngFile = this.fc.getSelectedFile();
        if (pngFile.exists() && JOptionPane.showConfirmDialog((Component)canvas, "File already exists.\nOverwrite?", "Export as PNG", 0, 2) != 0) {
            return;
        }
        BufferedImage im = new BufferedImage(canvas.getWidth(), canvas.getHeight(), 1);
        if (im == null) return;
        Graphics2D g2 = im.createGraphics();
        canvas.paint(g2);
        try {
            ImageIO.write((RenderedImage)im, "png", pngFile);
            return;
        }
        catch (IOException e) {
            System.out.println(new StringBuffer("IO Exception: ").append(e).toString());
        }
    }
}
