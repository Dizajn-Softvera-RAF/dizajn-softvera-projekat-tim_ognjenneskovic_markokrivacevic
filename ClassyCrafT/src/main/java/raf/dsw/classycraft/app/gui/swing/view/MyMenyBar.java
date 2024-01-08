package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar() {
        JMenu fileMenu = new JMenu("File");
        MainFrame.getInstance();
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteChildAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAuthorNameAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());


        JMenu source = new JMenu("Edit");
        source.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        source.add(MainFrame.getInstance().getActionManager().getUndoAction());
        source.add(MainFrame.getInstance().getActionManager().getRedoAction());

        add(fileMenu);
        add(source);

    }
}
