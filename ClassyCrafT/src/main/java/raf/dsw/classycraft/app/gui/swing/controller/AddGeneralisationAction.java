package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddGeneralisationAction extends AbstractClassyAction{

    public AddGeneralisationAction(){
        putValue(NAME,"Add Generalisation");
        putValue(SHORT_DESCRIPTION,"Add Generalisation");
        putValue(SMALL_ICON,loadIcon("/images/generalizacija.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddInheritanceState(Generalizacija.class);
        }
    }
}
