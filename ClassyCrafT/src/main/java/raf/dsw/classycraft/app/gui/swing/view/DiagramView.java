package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.*;
import raf.dsw.classycraft.app.geometry.Utils;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.DiagramMouseListener;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiagramView extends JPanel
{
    @Getter
    private final ClassyTreeItem selectedDiagram;
    @Getter
    private final ArrayList<ElementPainter> painters = new ArrayList<>();
    @Getter
    private final StateManager stateManager = new StateManager();
    @Getter
    @Setter
    private TempRectanglePainter tempRectPainter = null;
    @Getter
    @Setter
    private TempArrowPainter tempArrowPainter = null;

    @Getter
    private ZoomController zoomController;
    public DiagramView()
    {
        this.selectedDiagram = MainFrame.getInstance().getClassyTree().getSelectedNode();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseMotionListener(new DiagramMouseListener(this));
        addMouseListener(new DiagramMouseListener(this));
        zoomController = new ZoomController();
    }
    public void addPainter(ElementPainter painter)
    {
        painters.add(painter);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        var graphics = (Graphics2D) g;
        zoomController.applyTransform(graphics);
        for (var painter : painters)
            painter.paint(graphics);
        if (tempRectPainter != null)
            tempRectPainter.paint(graphics);
        if (tempArrowPainter != null)
            tempArrowPainter.paint(graphics);
    }
    public boolean canAddInterClass(Interclass interclass, Rectangle boundingBox)
    {
        for(var painter: painters)
        {
            // Don't check intersections with connections
            if (painter.getElement() instanceof Connection)
                continue;
            var boundingBox1 = painter.getBoundingBox();
            if(Utils.rectanglesOverlap(boundingBox, boundingBox1))
                return false;
        }
        return true;
    }
    public ElementPainter getInterclassPainterAt(int x, int y)
    {
        for (var painter: painters)
        {
            if (painter.getElement() instanceof Interclass)
            {
                var boundingBox = painter.getBoundingBox();
                if (boundingBox.contains(x, y))
                    return painter;
            }
        }
        return null;
    }
    public List<ElementPainter> getSelectedPainters()
    {
        return painters.stream().filter(painter -> painter.getElement().isSelected()).collect(Collectors.toList());
    }
    public void paint()
    {
        repaint();
    }

    public <T extends Interclass> void startAddClassState(Class<T> interclassClass) {
        stateManager.setAddInterclassState(interclassClass);
    }
    public <T extends Connection> void startAddConnectionState(Class<T> connectionClass)
    {
        stateManager.setAddConnectionState(connectionClass);
    }
    public void startSelectState()
    {
        stateManager.setSelectState();
    }
    public void startZoomInState()
    {
        stateManager.setZoomInState();
    }
    public void startZoomOutState() { stateManager.setZoomOutState();}
    public void startPanState() { stateManager.setPanState();}
    public void startMoveState () { stateManager.setMoveState();}

    public void startDuplicateDiagramElementState() { stateManager.setDuplicateDiagramElementState(); }
}
