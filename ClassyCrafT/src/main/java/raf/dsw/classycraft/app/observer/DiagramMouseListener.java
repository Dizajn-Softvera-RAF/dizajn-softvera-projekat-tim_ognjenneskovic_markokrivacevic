package raf.dsw.classycraft.app.observer;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DiagramMouseListener extends MouseAdapter
{
    private DiagramView diagramView;
    public DiagramMouseListener(DiagramView diagramView)
    {
        this.diagramView = diagramView;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        diagramView.getStateManager().getCurrentState().mousePressed(e.getX(), e.getY(), diagramView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        diagramView.getStateManager().getCurrentState().mouseReleased(e.getX(), e.getY(), diagramView);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        diagramView.getStateManager().getCurrentState().mouseDragged(e.getX(), e.getY(), diagramView);
    }
}
