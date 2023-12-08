package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddClassAction extends AbstractClassyAction{
    public AddClassAction(){
        // TODO: Implement this
        putValue(NAME,"Add Class");
        putValue(SHORT_DESCRIPTION,"Add Class");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().startAddClassState();
    }
}
