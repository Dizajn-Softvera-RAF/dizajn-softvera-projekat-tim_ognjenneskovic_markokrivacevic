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
    @Getter
    protected boolean selected = false;
    public DiagramElement(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent);
        this.color = color;
        this.strokeWidth = strokeWidth;
    }
    public void markSelected()
    {
        this.color = Color.YELLOW;
        this.selected = true;
    }
    public void markUnselected()
    {
        this.color = Color.BLACK;
        this.selected = false;
    }
}
