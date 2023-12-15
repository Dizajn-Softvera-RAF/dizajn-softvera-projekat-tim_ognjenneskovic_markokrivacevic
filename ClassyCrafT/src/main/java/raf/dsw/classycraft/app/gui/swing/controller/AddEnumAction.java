package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddEnumAction extends AbstractClassyAction{
    public AddEnumAction(){
        // TODO: Implement this
        putValue(NAME,"Add Enum");
        putValue(SHORT_DESCRIPTION,"Add Enum");
        putValue(SMALL_ICON,loadIcon("/images/plus_orange.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddClassState(Enum.class);
        }
    }
}
