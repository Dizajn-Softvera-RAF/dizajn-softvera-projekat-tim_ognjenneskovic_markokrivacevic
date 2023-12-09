package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempArrowPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class AddInheritanceState implements State
{
    private Interclass nodeFrom;
    private Interclass nodeTo;

    /**
     * Returns one of four points (top center, left center, etc.) on rectangle based on x and y coordinates
     */
    private Point getPointOnRect(Rectangle rect, int x, int y)
    {
        var topCenter = new Point(rect.x + rect.width / 2, rect.y);
        var leftCenter = new Point(rect.x, rect.y + rect.height / 2);
        var bottomCenter = new Point(rect.x + rect.width / 2, rect.y + rect.height);
        var rightCenter = new Point(rect.x + rect.width, rect.y + rect.height / 2);
        var points = new Point[]{topCenter, leftCenter, bottomCenter, rightCenter};
        // Pick closest point
        var closestPoint = points[0];
        var closestDistance = points[0].distance(x, y);
        for (var point : points)
        {
            if (point.distance(x, y) < closestDistance) {
                closestPoint = point;
                closestDistance = point.distance(x, y);
            }
        }
        return closestPoint;
    }
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {
        // Start drawing new arrow
        if(diagramView.getTempArrowPainter() == null)
        {
            var painter = diagramView.getInterclassPainterAt(x, y);
            if (painter == null) // If no node is clicked don't start drawing arrow
                return;
            var node = (Interclass) painter.getElement();

            nodeFrom = node;
            nodeFrom.markSelected();
            var startPoint = getPointOnRect(painter.getBoundingBox(), x, y);
            diagramView.setTempArrowPainter(new TempArrowPainter(null, startPoint.x, startPoint.y));
        }
        // Finish drawing arrow
        else
        {
            var painter = diagramView.getInterclassPainterAt(x, y);
            // If no node is clicked or same node is clicked don't finish drawing arrow
            if(painter == null)
                return;
            var node = (Interclass) painter.getElement();
            if (node == nodeFrom)
                return;
            nodeTo = node;

            var diagram = diagramView.getSelectedDiagram().getClassyNode();
            var newConnection = new Generalizacija(diagram, Color.BLACK, 5, nodeFrom, nodeTo);

            var tempArrowPainter = diagramView.getTempArrowPainter();
            var endPoint = getPointOnRect(painter.getBoundingBox(), tempArrowPainter.getEndX(), tempArrowPainter.getEndY());

            var connectionPainter = new ConnectionPainter(newConnection, tempArrowPainter.getX(), tempArrowPainter.getY(),
                    endPoint.x, endPoint.y);
            diagramView.addPainter(connectionPainter);
            MainFrame.getInstance().getClassyTree().addChild(diagramView.getSelectedDiagram());
            diagramView.setTempArrowPainter(null);
            nodeFrom.markUnselected();
            diagramView.paint();
        }
    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {
        if (diagramView.getTempArrowPainter() != null)
        {
            diagramView.getTempArrowPainter().updateEndPos(x, y);
            diagramView.paint();
        }
    }
}
