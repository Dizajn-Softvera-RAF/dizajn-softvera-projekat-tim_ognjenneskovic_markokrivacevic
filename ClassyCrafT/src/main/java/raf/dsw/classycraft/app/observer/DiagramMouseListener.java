package raf.dsw.classycraft.app.observer;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.state.concrete.ZoomInState;
import raf.dsw.classycraft.app.state.concrete.ZoomOutState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DiagramMouseListener extends MouseAdapter
{
    private DiagramView diagramView;
    public DiagramMouseListener(DiagramView diagramView)
    {
        this.diagramView = diagramView;
    }
    private void passEventToState(MouseFunction f, MouseEvent e)
    {
        // The clicked (x,y) is the position in the transformed coordinate system, get the original position
        // Unless the state iz ZoomIn / ZoomOut
        if(diagramView.getStateManager().getCurrentState() instanceof ZoomInState ||
                diagramView.getStateManager().getCurrentState() instanceof ZoomOutState)
            f.handleEvent(e.getX(), e.getY(), diagramView);
        else {
            var originalPoint = diagramView.getZoomController().getOriginalPoint(new Point(e.getX(), e.getY()));
            f.handleEvent(originalPoint.x, originalPoint.y, diagramView);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        passEventToState((x, y, diagramView) -> diagramView.getStateManager().getCurrentState().mouseClicked(x, y, diagramView), e);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        passEventToState((x, y, diagramView) -> diagramView.getStateManager().getCurrentState().mousePressed(x, y, diagramView), e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        passEventToState((x, y, diagramView) -> diagramView.getStateManager().getCurrentState().mouseReleased(x, y, diagramView), e);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        passEventToState((x, y, diagramView) -> diagramView.getStateManager().getCurrentState().mouseMoved(x, y, diagramView), e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        passEventToState((x, y, diagramView) -> diagramView.getStateManager().getCurrentState().mouseDragged(x, y, diagramView), e);
    }
}
