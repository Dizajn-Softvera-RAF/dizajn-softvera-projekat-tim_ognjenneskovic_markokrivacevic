package raf.dsw.classycraft.app.classyRepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class ElementPainter
{
    @Getter
    protected DiagramElement element;
    @Getter
    protected int x;
    @Getter
    protected int y;
    @Getter
    @Setter
    protected AffineTransform affineTransform;
    public ElementPainter(DiagramElement element, int x, int y)
    {
        this.element = element;
        this.x = x;
        this.y = y;
        this.affineTransform = new AffineTransform();
    }
    public abstract void paint(Graphics2D g);
    public abstract Rectangle getBoundingBox();
    public void applyTransformToPoints(AffineTransform transform)
    {
        var point = new Point(x, y);
        transform.transform(point, point);
        x = point.x;
        y = point.y;
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
