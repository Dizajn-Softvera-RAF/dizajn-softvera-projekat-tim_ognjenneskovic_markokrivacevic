package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.observer.DiagramMouseListener;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel
{
    private Diagram selectedDiagram;
    private final ArrayList<DiagramElement> elements = new ArrayList<>();
    private final ArrayList<ElementPainter> painters = new ArrayList<>();
    @Getter
    private final StateManager stateManager = new StateManager();
    public DiagramView()
    {
        this.selectedDiagram = null;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        addMouseListener(new DiagramMouseListener(this));

        // Just for testing TODO: Remove
        var element = new Klasa("Klasa", null, Color.GRAY, 5);
        elements.add(element);
        var painter = new InterClassPainter(element);
        painters.add(painter);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (var painter : painters)
            painter.paint(g);
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
