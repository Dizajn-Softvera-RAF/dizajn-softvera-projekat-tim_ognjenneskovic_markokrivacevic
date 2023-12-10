package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode
{
    @Getter
    protected Color color;
    protected int strokeWidth;
    public DiagramElement(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent);
        this.color = color;
        this.strokeWidth = strokeWidth;
    }
    public void markSelected()
    {
        this.color = Color.YELLOW;
    }
    public void markUnselected()
    {
        this.color = Color.BLACK;
    }
}
