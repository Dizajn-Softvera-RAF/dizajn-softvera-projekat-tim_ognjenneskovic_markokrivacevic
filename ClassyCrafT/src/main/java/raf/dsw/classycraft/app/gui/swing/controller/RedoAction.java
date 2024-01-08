package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RedoAction extends AbstractClassyAction{
    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Y"));
        putValue(NAME,"Redo");
        putValue(SHORT_DESCRIPTION,"Redo");
        putValue(SMALL_ICON,loadIcon("/images/redo.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.getCommandManager().redo();
        }
    }
}
