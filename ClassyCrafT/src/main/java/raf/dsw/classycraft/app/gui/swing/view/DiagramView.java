package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempArrowPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.geometry.Utils;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.DiagramMouseListener;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    public DiagramView()
    {
        this.selectedDiagram = MainFrame.getInstance().getClassyTree().getSelectedNode();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseMotionListener(new DiagramMouseListener(this));
        addMouseListener(new DiagramMouseListener(this));
    }
    public void addPainter(ElementPainter painter)
    {
        painters.add(painter);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (var painter : painters)
            painter.paint(g);
        if (tempRectPainter != null)
            tempRectPainter.paint(g);
        if (tempArrowPainter != null)
            tempArrowPainter.paint(g);
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
    public void paint()
    {
        repaint();
    }

    public void startAddClassState()
    {
        stateManager.setAddClassState();
    }
    public void startAddInheritanceState()
    {
        stateManager.setAddInheritanceState();
    }
    public void startSelectState()
    {
        stateManager.setSelectState();
    }
}
