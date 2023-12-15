package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddClassAction extends AbstractClassyAction{
    public AddClassAction(){
        putValue(NAME,"Add Class");
        putValue(SHORT_DESCRIPTION,"Add Class");
        putValue(SMALL_ICON,loadIcon("/images/plus_green.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddClassState(Klasa.class);
        }
    }
}
