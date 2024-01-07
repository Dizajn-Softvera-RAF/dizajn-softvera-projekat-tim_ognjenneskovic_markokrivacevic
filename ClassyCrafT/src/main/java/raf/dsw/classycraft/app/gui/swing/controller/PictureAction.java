package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureAction extends AbstractClassyAction{

    public PictureAction(){
        putValue(NAME,"Picture");
        putValue(SHORT_DESCRIPTION,"Save as picture");
        putValue(SMALL_ICON,loadIcon("/images/camera.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = MainFrame.getInstance().getTabbedPanel();
        DiagramView diagramView = packageView.getSelectedDiagramView();

        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File filePicture = jfc.getSelectedFile();

            BufferedImage image = diagramView.createImage();
            System.out.println(image);

            try {
                ImageIO.write(image, "png", new File(filePicture.getAbsolutePath()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
