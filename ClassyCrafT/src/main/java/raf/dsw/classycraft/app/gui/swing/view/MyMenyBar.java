package raf.dsw.classycraft.app.gui.swing.view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        fileMenu.setMnemonic(KeyEvent.VK_F);

        var actionManager = MainFrame.getInstance().getActionManager();

        fileMenu.add(actionManager.getExitAction());
        fileMenu.add(actionManager.getNewProjectAction());
        fileMenu.add(actionManager.getNewPackageAction());
        fileMenu.add(actionManager.getNewDiagramAction());

        add(fileMenu);

        editMenu.add(actionManager.getAboutUsAction());

        add(editMenu);
    }

}
