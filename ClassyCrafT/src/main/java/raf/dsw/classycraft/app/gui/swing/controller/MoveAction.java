package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractClassyAction{
    public MoveAction(){
        // TODO: Implement this
        putValue(NAME,"Move");
        putValue(SHORT_DESCRIPTION,"Move");
        putValue(SMALL_ICON,loadIcon("/images/move.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startMoveState();
        }
    }
}
