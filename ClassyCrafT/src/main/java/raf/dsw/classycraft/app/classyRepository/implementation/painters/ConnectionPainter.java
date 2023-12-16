package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.geometry.linalg.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ConnectionPainter<T extends Connection> extends ElementPainter {
    @Getter
    private int endX, endY;
    private final Class<T> connectionClass;
    public ConnectionPainter(Connection element, int startX, int startY, int endX, int endY) {
        super(element, startX, startY);
        this.endX = endX;
        this.endY = endY;
        connectionClass = (Class<T>) element.getClass();
    }

    @Override
    public ElementPainter copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void paint(Graphics2D g) {
        var arrowDrawer = new ArrowDrawer(10);
        arrowDrawer.drawArrow(g, connectionClass, x, y, endX, endY, element.getCurrentColor());
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
    public void setStartPoint(Point point)
    {
        x = point.x;
        y = point.y;
    }
    public void setEndPoint(Point point)
    {
        endX = point.x;
        endY = point.y;
    }
}
