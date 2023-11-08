package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction ea = new ExitAction();
        fileMenu.add(ea);

        var newProjectAction = new NewProjectAction();
        fileMenu.add(newProjectAction);
        var newPacketAction = new NewPacketAction();
        fileMenu.add(newPacketAction);
        var newDiagramAction = new NewDiagramAction();
        fileMenu.add(newDiagramAction);

        add(fileMenu);
        var aboutUs = new AboutUsAction();
        editMenu.add(aboutUs);
        add(editMenu);
    }

}
