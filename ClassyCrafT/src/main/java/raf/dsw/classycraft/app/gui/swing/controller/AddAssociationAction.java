package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddAssociationAction extends AbstractClassyAction{

    public AddAssociationAction(){
        putValue(NAME,"Add Association");
        putValue(SHORT_DESCRIPTION,"Add Associaton");
        putValue(SMALL_ICON,loadIcon("/images/asocijacija.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startAddInheritanceState();
        }
    }
}
