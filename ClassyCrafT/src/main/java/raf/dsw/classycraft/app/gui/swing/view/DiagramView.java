package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel
{
    private Diagram selectedDiagram;
    private final ArrayList<DiagramElement> elements = new ArrayList<>();
    private final ArrayList<ElementPainter> painters = new ArrayList<>();
    public DiagramView()
    {
        this.selectedDiagram = null;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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
}
