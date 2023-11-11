package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractClassyAction {

    public ExitAction(){
        var icon = loadIcon("/images/exit.png");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, icon);
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
