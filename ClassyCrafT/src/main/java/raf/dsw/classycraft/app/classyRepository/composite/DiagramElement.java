package raf.dsw.classycraft.app.classyRepository.composite;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode
{
    protected Color color;
    protected int strokeWidth;
    public DiagramElement(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent);
        this.color = color;
        this.strokeWidth = strokeWidth;
    }
}
