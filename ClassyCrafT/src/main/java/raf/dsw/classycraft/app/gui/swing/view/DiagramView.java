package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.*;
import raf.dsw.classycraft.app.geometry.Utils;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.DiagramMouseListener;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    public DiagramView(Diagram diagram)
    {
        this.selectedDiagram = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findNode(diagram);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseMotionListener(new DiagramMouseListener(this));
        addMouseListener(new DiagramMouseListener(this));
        zoomController = new ZoomController();
        var diagramElements = ((ClassyNodeComposite)selectedDiagram.getClassyNode()).getChildren();
        for(var child: diagramElements)
        {
            if(child instanceof Interclass)
            {
                var interclass = (Interclass) child;
                var interclassPainter = new InterClassPainter(interclass, interclass.getX(), interclass.getY());
                painters.add(interclassPainter);
            }
            else if(child instanceof Connection)
            {
                var connection = (Connection) child;
                var connectionPainter = new ConnectionPainter(connection, connection.getX(), connection.getY(),
                        connection.getEndX(), connection.getEndY());
                painters.add(connectionPainter);
            }
        }
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

    public void deletePainterForCurrent(ElementPainter painter){
        if(painter.getElement() == null) {
            repaint();
            return;
        }
        selectedDiagram.remove((MutableTreeNode) painter.getElement());
        painters.remove(painter);
        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).deleteInTree(painter.getElement());
        repaint();
    }

    public void addPainterForCurrent(ElementPainter painter){
        painters.add(painter);
        selectedDiagram.add((MutableTreeNode) painter);
        ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).addChild(selectedDiagram);
        repaint();
    }

    public BufferedImage createImage() {
        // Nadji validne koordinate
        // min ti je koordinata pojma najblizeg 0,0
        int minY = 1000;
        int minX = 1000;
        int maxY = 0;
        int maxX = 0;
//        for (ElementPainter p: this.getPainters()){
//            DiagramElement elem = p.getElement();
//            if (elem instanceof Interclass){
//                System.out.println(((Interclass) elem).() + " " + ((Topic) elem).getY());
//                if ( maxX < ((Topic) elem).getX()) maxX = (int) ((Topic) elem).getX();
//                if ( maxY < ((Topic) elem).getY()) maxY = (int) ((Topic) elem).getY();
//                if ( minX > ((Topic) elem).getX()) minX = (int) ((Topic) elem).getX();
//                if ( minY > ((Topic) elem).getY()) minY = (int) ((Topic) elem).getY();
//            }
//        }
        int desiredWidth = 620;
        int desiredHeight = 360;

        BufferedImage bufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        this.paint(g);
        return bufferedImage;
    }

}
