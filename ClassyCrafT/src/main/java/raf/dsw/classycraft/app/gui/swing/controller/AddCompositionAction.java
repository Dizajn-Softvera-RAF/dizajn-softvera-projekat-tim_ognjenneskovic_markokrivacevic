package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddCompositionAction extends AbstractClassyAction {

    public AddCompositionAction(){
        putValue(NAME,"Add Composition");
        putValue(SHORT_DESCRIPTION,"Add Composition");
        putValue(SMALL_ICON,loadIcon("/images/kompozicija.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddCompositionState();
        }
    }
}