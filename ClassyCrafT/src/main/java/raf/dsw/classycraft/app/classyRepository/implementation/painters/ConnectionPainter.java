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
    private final Class<T> connectionClass;
    private final Connection connection;
    public ConnectionPainter(Connection element, int startX, int startY, int endX, int endY) {
        super(element, startX, startY);
        this.connection = element;
        connectionClass = (Class<T>) element.getClass();
    }
    public int getEndX() {
        return connection.getEndX();
    }
    public int getEndY() {
        return connection.getEndY();
    }
    private void setEndX(int endX) {
        connection.setEndX(endX);
    }
    private void setEndY(int endY) {
        connection.setEndY(endY);
    }

    @Override
    public ElementPainter copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void paint(Graphics2D g) {
        var arrowDrawer = new ArrowDrawer(10);
        arrowDrawer.drawArrow(g, connectionClass, getX(), getY(), getEndX(), getEndY(), element.getCurrentColor());
    }

    @Override
    public Rectangle getBoundingBox() {
        int width = Math.abs(getEndX() - getX());
        int height = Math.abs(getEndY() - getY());
        return new Rectangle(getX(),getY(),width,height);
    }
    @Override
    public void applyTransformToPoints(AffineTransform transform)
    {
        super.applyTransformToPoints(transform);
        var point = new Point(getEndX(), getEndY());
        transform.transform(point, point);
        setEndX(point.x);
        setEndY(point.y);
    }
    public void setStartPoint(Point point)
    {
        setX(point.x);
        setY(point.y);
    }
    public void setEndPoint(Point point)
    {
        setEndX(point.x);
        setEndY(point.y);
    }
}
