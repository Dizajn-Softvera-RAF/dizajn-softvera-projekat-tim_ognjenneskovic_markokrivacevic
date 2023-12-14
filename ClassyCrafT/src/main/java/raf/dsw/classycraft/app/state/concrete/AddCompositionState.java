package raf.dsw.classycraft.app.state.concrete;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Kompozicija;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.CompositionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class AddCompositionState implements State {

    private Interclass nodeFrom = null;
    private Interclass nodeTo = null;
    private Point startPoint = null;

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
        if(nodeFrom==null) {
            var painter = diagramView.getInterclassPainterAt(x, y);
            if (painter == null)
                return;

            var node = (Interclass) painter.getElement();

            nodeFrom = node;
            nodeFrom.markSelected();
            startPoint = getPointOnRect(painter.getBoundingBox(),x,y);
            diagramView.setCompositionPainter(new CompositionPainter(null, startPoint.x, startPoint.y));
        }else{
            var painter = diagramView.getInterclassPainterAt(x,y);
            if(painter==null)
                return;
            var node = (Interclass) painter.getElement();
            if(node==nodeFrom)
                return;
            nodeTo = node;
            var diagram = diagramView.getSelectedDiagram().getClassyNode();
            var newConnection = new Kompozicija(diagram,Color.BLACK,5,nodeFrom,nodeTo);

            var compositionPainter = diagramView.getCompositionPainter();
            var endPoint = getPointOnRect(painter.getBoundingBox(),compositionPainter.getEndX(),compositionPainter.getEndY());

            var connectionPainter = new ConnectionPainter(newConnection,compositionPainter.getX(),compositionPainter.getY(), endPoint.x, endPoint.y);
            diagramView.addPainter(connectionPainter);
            MainFrame.getInstance().getClassyTree().addChild(diagramView.getSelectedDiagram());

            diagramView.setCompositionPainter(null);
            nodeFrom.markUnselected();
            nodeFrom=null;
            nodeTo=null;
            startPoint=null;
        }
      diagramView.paint();
    }

    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {
          if(nodeFrom!=null){
              diagramView.getCompositionPainter().updateEndPos(x,y);
              diagramView.paint();
          }
    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void stateExited() {
        if(nodeFrom!=null)
            nodeFrom.markUnselected();
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().setCompositionPainter(null);
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().paint();
    }
}
