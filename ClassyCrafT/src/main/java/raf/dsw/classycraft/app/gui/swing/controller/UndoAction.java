package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractClassyAction{
    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Z"));
        putValue(NAME,"Undo");
        putValue(SHORT_DESCRIPTION,"Undo");
        putValue(SMALL_ICON,loadIcon("/images/undo.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.getCommandManager().undo();
        }
    }
}
