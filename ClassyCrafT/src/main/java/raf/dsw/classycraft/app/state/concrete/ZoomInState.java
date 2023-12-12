package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.state.State;

public class ZoomInState implements State
{
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {
        diagramView.getZoomController().handleClick(x, y, diagramView, 1.25);
        diagramView.paint();
    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void stateExited() {

    }
}
