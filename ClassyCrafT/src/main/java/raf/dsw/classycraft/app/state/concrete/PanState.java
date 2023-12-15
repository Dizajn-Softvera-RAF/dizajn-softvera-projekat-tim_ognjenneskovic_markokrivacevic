package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PanState implements State
{
    private Point startPoint;
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {
        startPoint = new Point(x, y);
    }

    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {
        startPoint = null;
    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {
        if(startPoint == null)
            return;
        var dx = x - startPoint.x;
        var dy = y - startPoint.y;
        var transform = AffineTransform.getTranslateInstance(dx, dy);
        for(var painter: diagramView.getPainters())
        {
            // Move only interclass elements, the connections will be moved automatically
            if(painter instanceof InterClassPainter)
                painter.applyTransformToPoints(transform);
        }
        startPoint = new Point(x, y);
        diagramView.paint();
    }

    @Override
    public void stateExited() {

    }

}
