package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Zavisnost;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddDependencyAction extends AbstractClassyAction{

    public AddDependencyAction(){
        putValue(NAME,"Add Association");
        putValue(SHORT_DESCRIPTION,"Add Associaton");
        putValue(SMALL_ICON,loadIcon("/images/asocijacija.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddInheritanceState(Zavisnost.class);
        }
    }
}
