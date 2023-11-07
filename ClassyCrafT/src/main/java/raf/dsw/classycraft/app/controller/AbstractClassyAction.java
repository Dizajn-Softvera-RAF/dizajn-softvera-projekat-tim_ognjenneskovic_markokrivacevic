package raf.dsw.classycraft.app.controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

abstract public class AbstractClassyAction extends AbstractAction {
    public Icon loadIcon(String fileName)
    {
        return loadIcon(fileName, 30, 30);
    }
    public Icon loadIcon(String fileName, int width, int height)
    {
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null)
        {
            Image img = new ImageIcon(imageURL).getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            icon = new ImageIcon(scaledImg);
        }
        else
            System.err.println("Resource not found: " + fileName);

        return icon;
    }
}
