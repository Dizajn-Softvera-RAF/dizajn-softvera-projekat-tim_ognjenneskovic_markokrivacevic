package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public interface State
{
    void mousePressed(int x, int y, DiagramView diagramView);
    void mouseClicked(int x, int y, DiagramView diagramView);
    void mouseReleased(int x, int y, DiagramView diagramView);
    void mouseMoved(int x, int y, DiagramView diagramView);
    void mouseDragged(int x, int y, DiagramView diagramView);
    void stateExited(DiagramView diagramView);
}
