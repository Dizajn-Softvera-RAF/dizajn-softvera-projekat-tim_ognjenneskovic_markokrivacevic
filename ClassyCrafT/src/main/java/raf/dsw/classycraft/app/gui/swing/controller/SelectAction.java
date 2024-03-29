package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractClassyAction{
    public SelectAction(){
        putValue(NAME,"Select");
        putValue(SHORT_DESCRIPTION,"Select");
        putValue(SMALL_ICON,loadIcon("/images/select.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startSelectState();
        }
    }
}
