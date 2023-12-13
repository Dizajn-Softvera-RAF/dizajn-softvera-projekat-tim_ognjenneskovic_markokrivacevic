package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomToFitAction extends AbstractClassyAction{
    public ZoomToFitAction(){
        // TODO: Implement this
        putValue(NAME,"Zoom to fit");
        putValue(SHORT_DESCRIPTION,"Zoom to fit");
        putValue(SMALL_ICON,loadIcon("/images/zoom_to_fit.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.getZoomController().zoomToFit(selectedDiagramView);
        }
    }
}
