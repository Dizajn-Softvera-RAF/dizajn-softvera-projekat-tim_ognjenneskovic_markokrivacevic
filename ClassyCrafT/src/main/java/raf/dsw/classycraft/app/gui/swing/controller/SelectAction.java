package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractClassyAction{
    public SelectAction(){
        // TODO: Implement this
        putValue(NAME,"Select");
        putValue(SHORT_DESCRIPTION,"Select");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().startSelectState();
    }
}
