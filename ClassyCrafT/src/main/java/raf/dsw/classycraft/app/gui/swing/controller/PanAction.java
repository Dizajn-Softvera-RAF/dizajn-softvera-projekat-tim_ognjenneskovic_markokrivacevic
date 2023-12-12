package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class PanAction extends AbstractClassyAction{
    public PanAction(){
        // TODO: Implement this
        putValue(NAME,"Pan");
        putValue(SHORT_DESCRIPTION,"Pan");
        putValue(SMALL_ICON,loadIcon("/images/pan.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startPanState();
        }
    }
}
