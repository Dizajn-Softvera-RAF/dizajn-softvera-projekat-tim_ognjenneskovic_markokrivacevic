package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ConnectionPainter extends ElementPainter {
    private int endX, endY;
    public ConnectionPainter(DiagramElement element, int startX, int startY, int endX, int endY) {
        super(element, startX, startY);
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(element.getColor());
        g.drawLine(x, y, endX, endY);
    }

    @Override
    public Rectangle getBoundingBox() {
        int width = Math.abs(endX - x);
        int height = Math.abs(endY - y);
        return new Rectangle(x,y,width,height);
    }
    @Override
    public void applyTransformToPoints(AffineTransform transform)
    {
        super.applyTransformToPoints(transform);
        var point = new Point(endX, endY);
        transform.transform(point, point);
        endX = point.x;
        endY = point.y;
    }
}
