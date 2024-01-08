package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class ElementPainter
{
    @Getter
    @Setter
    protected DiagramElement element;
    @Getter
    @Setter
    protected AffineTransform affineTransform;
    public int getX() { return element.x; }
    public int getY() { return element.y; }
    protected void setX(int x) { element.x = x; }
    protected void setY(int y) { element.y = y; }
    public ElementPainter(DiagramElement element, int x, int y)
    {
        this.element = element;
        element.x = x;
        element.y = y;
        this.affineTransform = new AffineTransform();
    }
    public ElementPainter(ElementPainter other)
    {
        this.element = other.element.copy();
        this.affineTransform = new AffineTransform(other.affineTransform);
    }

    public abstract ElementPainter copy();
    public abstract void paint(Graphics2D g);
    public abstract Rectangle getBoundingBox();
    public void applyTransformToPoints(AffineTransform transform)
    {
        var point = new Point(element.x, element.y);
        transform.transform(point, point);
        element.x = point.x;
        element.y = point.y;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof ElementPainter))
            return false;
        var other = (ElementPainter)obj;
        return element.equals(other.element);
    }
}
