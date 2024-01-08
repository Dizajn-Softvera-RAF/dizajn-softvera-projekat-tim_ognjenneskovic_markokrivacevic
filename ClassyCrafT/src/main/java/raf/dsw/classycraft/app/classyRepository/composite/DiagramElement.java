package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode
{
    @Getter @Setter
    int x;
    @Getter @Setter
    int y;
    @Getter
    protected Color currentColor;
    private Color defaultColor;
    @Getter
    protected int strokeWidth;
    @Getter
    protected boolean selected = false;
    public DiagramElement(String name, ClassyNode parent, Color defaultColor, int strokeWidth, int x, int y) {
        super(name, parent);
        this.currentColor = defaultColor;
        this.defaultColor = defaultColor;
        this.strokeWidth = strokeWidth;
        this.x = x;
        this.y = y;
    }
    public DiagramElement(DiagramElement other)
    {
        super(other);
        this.currentColor = other.currentColor;
        this.defaultColor = other.defaultColor;
        this.strokeWidth = other.strokeWidth;
    }
    public abstract DiagramElement copy();
    public void markSelected()
    {
        this.currentColor = Color.YELLOW;
        this.selected = true;
    }
    public void markUnselected()
    {
        this.currentColor = defaultColor;
        this.selected = false;
    }
}
