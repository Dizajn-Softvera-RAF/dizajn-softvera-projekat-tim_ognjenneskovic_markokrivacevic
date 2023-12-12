package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractClassyAction{
    public ZoomInAction(){
        // TODO: Implement this
        putValue(NAME,"Zoom In");
        putValue(SHORT_DESCRIPTION,"Zoom In");
        putValue(SMALL_ICON,loadIcon("/images/zoom_in.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startZoomInState();
        }
    }
}
