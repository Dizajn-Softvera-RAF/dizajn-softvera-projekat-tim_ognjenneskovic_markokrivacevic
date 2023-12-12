package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractClassyAction{
    public ZoomOutAction(){
        // TODO: Implement this
        putValue(NAME,"Zoom Out");
        putValue(SHORT_DESCRIPTION,"Zoom Out");
        putValue(SMALL_ICON,loadIcon("/images/zoom_out.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startZoomOutState();
        }
    }
}
