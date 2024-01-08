package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.command.MoveCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class MoveState implements State
{
    private Point startPoint;
    private ElementPainter clickedPainter;
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {
        clickedPainter = diagramView.getInterclassPainterAt(x, y);
        if(clickedPainter == null) {
            deselectAll(diagramView);
            diagramView.paint();
        }
        else {
            clickedPainter.getElement().markSelected();
            startPoint = new Point(x, y);
        }
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
    private void deselectAll(DiagramView diagramView)
    {
        for(var painter: diagramView.getPainters())
            painter.getElement().markUnselected();
    }
    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {
        if(startPoint == null)
            return;
        var dx = x - startPoint.x;
        var dy = y - startPoint.y;
        var transform = AffineTransform.getTranslateInstance(dx, dy);
        // Check if there are multiple selected elements
        var selectedPainters = new ArrayList<ElementPainter>();
        for(var painter: diagramView.getPainters())
            if(painter.getElement().isSelected())
                selectedPainters.add(painter);

        if(clickedPainter == null)
        {
            deselectAll(diagramView);
            diagramView.paint();
            return;
        }
        else if(!selectedPainters.contains(clickedPainter))
        {
            deselectAll(diagramView);
            clickedPainter.getElement().markSelected();
            selectedPainters.add(clickedPainter);
        }
        var cmdManager = diagramView.getCommandManager();
        var moveCommand = new MoveCommand(selectedPainters, transform);
        cmdManager.executeCommand(moveCommand);

        startPoint = new Point(x, y);
    }

    @Override
    public void stateExited() {

    }

}
