package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.geometry.linalg.Vector;

import java.awt.*;

public class ArrowDrawer
{
    private int arrowSize;
    public ArrowDrawer(int arrowSize)
    {
        this.arrowSize = arrowSize;
    }
    public void drawAggregationArrow(Graphics2D g, int x, int y, int endX, int endY, Color lineColor)
    {

    }
    public void drawGeneralizationArrow(Graphics2D g, int x, int y, int endX, int endY, Color lineColor)
    {

    }
    public void drawCompositionArrow(Graphics2D g, int x, int y, int endX, int endY)
    {

    }
    public void drawDependencyArrow(Graphics2D g, int x, int y, int endX, int endY)
    {
        var directionVec = new Vector(new Point(x, y), new Point(endX, endY));
        directionVec = directionVec.normalized();
        var normalVec = directionVec.getOrthogonal();

        // Arrow points
        var center = new Vector(endX, endY);
        center = center.sub(directionVec.mul(arrowSize));
        arrowSize /= 2;
        var up = center.add(normalVec.mul(arrowSize));
        var down = center.sub(normalVec.mul(arrowSize));

        // Draw arrow
        var poly = new Polygon();
        poly.addPoint((int)up.getX(), (int)up.getY());
        poly.addPoint((int)down.getX(), (int)down.getY());
        poly.addPoint(endX, endY);
        g.fillPolygon(poly);
    }
}
