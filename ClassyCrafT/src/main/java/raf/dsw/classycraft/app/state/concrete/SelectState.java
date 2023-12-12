package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.geometry.Utils;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class SelectState implements State
{
    private Point startPoint = null;
    private Point endPoint;
    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {

    }
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {
        startPoint = new Point(x, y);
    }
    private void markSelected(DiagramView diagramView, Rectangle rect)
    {
        for(var painter: diagramView.getPainters())
        {
            if (painter.getElement() instanceof Interclass)
            {
                var boundingBox1 = painter.getBoundingBox();
                if (Utils.rectanglesOverlap(rect, boundingBox1))
                    painter.getElement().markSelected();
                else
                    painter.getElement().markUnselected();
            }
        }
    }
    private void updateRect(DiagramView diagramView)
    {
        int width = Math.abs(endPoint.x - startPoint.x);
        int height = Math.abs(endPoint.y - startPoint.y);
        // Get top left corner of rectangle
        var topLeft = new Point(startPoint.x, startPoint.y);
        if (endPoint.x < topLeft.x)
            topLeft.x = endPoint.x;
        if (endPoint.y < topLeft.y)
            topLeft.y = endPoint.y;


        var rect = new Rectangle(topLeft.x, topLeft.y, width, height);
        // Update which elements are selected
        markSelected(diagramView, rect);
        // Update temp rect painter
        diagramView.setTempRectPainter(new TempRectanglePainter(null, topLeft.x, topLeft.y, width, height));
        // Force repaint
        diagramView.paint();
    }
    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {
        endPoint = new Point(x, y);
        updateRect(diagramView);
        startPoint = null;
        endPoint = null;
        diagramView.setTempRectPainter(null);
    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {
        if (startPoint != null) {
            endPoint = new Point(x, y);
            updateRect(diagramView);
        }
    }

    @Override
    public void stateExited() {
        startPoint = null;
        endPoint = null;
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().setTempRectPainter(null);
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().paint();
    }
}
