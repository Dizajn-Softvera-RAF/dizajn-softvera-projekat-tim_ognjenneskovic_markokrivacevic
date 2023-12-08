package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.css.Rect;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Atribut;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Metod;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.geometry.Utils;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
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
    private final ArrayList<ElementPainter> painters = new ArrayList<>();
    @Getter
    private final StateManager stateManager = new StateManager();
    @Getter
    @Setter
    private TempRectanglePainter tempRectPainter = null;
    public DiagramView()
    {
        this.selectedDiagram = MainFrame.getInstance().getClassyTree().getSelectedNode();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseMotionListener(new DiagramMouseListener(this));
        addMouseListener(new DiagramMouseListener(this));
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (var painter : painters)
            painter.paint(g);
        if (tempRectPainter != null)
            tempRectPainter.paint(g);
    }
    private boolean canAddInterClass(Interclass interclass, Rectangle boundingBox)
    {
        for(var painter: painters)
        {
            var boundingBox1 = painter.getBoundingBox(getGraphics());
            if(Utils.rectanglesOverlap(boundingBox, boundingBox1))
                return false;
        }
        return true;
    }
    public void addElement(DiagramElement element, int x, int y)
    {
        var painter = new InterClassPainter(element, x, y);
        if (element instanceof Interclass)
        {
            var bb = painter.getBoundingBox(getGraphics());
            if (canAddInterClass((Interclass)element, bb))
            {
                painters.add(painter);
                MainFrame.getInstance().getClassyTree().addChild(selectedDiagram);
            }
        }
        else
        {
            painters.add(painter);
            MainFrame.getInstance().getClassyTree().addChild(selectedDiagram);
        }
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
