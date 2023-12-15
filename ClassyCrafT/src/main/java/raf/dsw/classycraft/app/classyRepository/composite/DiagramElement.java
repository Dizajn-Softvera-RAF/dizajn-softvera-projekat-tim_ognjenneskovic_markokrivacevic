package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;

import java.awt.*;

public abstract class DiagramElement extends ClassyNode
{
    @Getter
    protected Color currentColor;
    private Color defaultColor;
    protected int strokeWidth;
    @Getter
    protected boolean selected = false;
    public DiagramElement(String name, ClassyNode parent, Color defaultColor, int strokeWidth) {
        super(name, parent);
        this.currentColor = defaultColor;
        this.defaultColor = defaultColor;
        this.strokeWidth = strokeWidth;
    }
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
