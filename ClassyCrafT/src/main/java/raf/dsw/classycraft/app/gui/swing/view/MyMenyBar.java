package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.gui.swing.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar() {
        JMenu fileMenu = new JMenu("File");
        MainFrame.getInstance();
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteChildAction());

        JMenu source = new JMenu("Edit");
        source.add(MainFrame.getInstance().getActionManager().getAboutUsAction());

        add(fileMenu);
        add(source);

    }
}
