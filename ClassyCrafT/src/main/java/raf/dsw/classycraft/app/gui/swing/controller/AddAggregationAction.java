package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddAggregationAction extends AbstractClassyAction {
    public AddAggregationAction(){
        putValue(NAME,"Add Aggregation");
        putValue(SHORT_DESCRIPTION,"Add Aggregation");
        putValue(SMALL_ICON,loadIcon("/images/agregacija.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddInheritanceState();
        }
    }
}
