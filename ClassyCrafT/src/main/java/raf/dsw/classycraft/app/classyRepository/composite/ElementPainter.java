package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class ElementPainter
{
    protected DiagramElement element;
    @Getter
    protected int x;
    @Getter
    protected int y;
    public ElementPainter(DiagramElement element, int x, int y)
    {
        this.element = element;
        this.x = x;
        this.y = y;
    }
    public abstract void paint(Graphics g);
    public abstract Rectangle getBoundingBox(Graphics g);
}
