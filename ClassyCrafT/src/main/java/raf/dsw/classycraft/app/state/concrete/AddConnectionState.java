package raf.dsw.classycraft.app.state.concrete;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.TempElement;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempArrowPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class AddConnectionState<T extends Connection> implements State
{
    @Getter
    private Interclass nodeFrom = null;
    @Getter
    private Interclass nodeTo = null;
    @Getter
    private Point endPoint;
    @Getter
    private final Class<T> connectionClass;
    public AddConnectionState(Class<T> connectionClass)
    {
        this.connectionClass = connectionClass;
    }

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
        if(nodeFrom == null)
        {
            var painter = diagramView.getInterclassPainterAt(x, y);
            if (painter == null) // If no node is clicked don't start drawing arrow
                return;

            nodeFrom = (Interclass) painter.getElement();
            nodeFrom.markSelected();
            var startPoint = getPointOnRect(painter.getBoundingBox(), x, y);
            diagramView.setTempArrowPainter(new TempArrowPainter(new TempElement(), startPoint.x, startPoint.y));
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

            makeConnection(diagramView,x,y);

            // Unset everything
            diagramView.setTempArrowPainter(null);
            nodeFrom.markUnselected();
            nodeFrom = null;
            nodeTo = null;
        }
        // Force repaint
        diagramView.paint();
    }
    private void makeConnection(DiagramView diagramView, int endX, int endY)
    {
        var diagram = (Diagram)diagramView.getSelectedDiagram().getClassyNode();
        var endPainter = diagramView.getInterclassPainterAt(endX, endY);

        var tempArrowPainter = diagramView.getTempArrowPainter();
        endPoint = getPointOnRect(endPainter.getBoundingBox(), tempArrowPainter.getEndX(), tempArrowPainter.getEndY());

        // Make the new connection using the factory
        MainFrame.getInstance().getClassyTree().addChild(diagramView.getSelectedDiagram());
        // Get the new connection
        var newConnection = (Connection) diagram.getChildren().get(diagram.getChildren().size() - 1);
        // Add painter for the new connection
        var connectionPainter =  new ConnectionPainter(newConnection, tempArrowPainter.getX(), tempArrowPainter.getY(),
                endPoint.x, endPoint.y);
        diagramView.addPainter(connectionPainter);

    }

    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {
        if(nodeFrom != null)
        {
            diagramView.getTempArrowPainter().updateEndPos(x, y);
            diagramView.paint();
        }
    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void stateExited() {
        if (nodeFrom != null)
            nodeFrom.markUnselected();
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().setTempArrowPainter(null);
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().paint();
    }
}
