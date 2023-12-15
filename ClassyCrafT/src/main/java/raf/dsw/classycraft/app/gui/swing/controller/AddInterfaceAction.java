package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Interfejs;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddInterfaceAction extends AbstractClassyAction{
    public AddInterfaceAction(){
        putValue(NAME,"Add Interface");
        putValue(SHORT_DESCRIPTION,"Add Interface");
        putValue(SMALL_ICON,loadIcon("/images/plus_yellow.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddClassState(Interfejs.class);
        }
    }
}
