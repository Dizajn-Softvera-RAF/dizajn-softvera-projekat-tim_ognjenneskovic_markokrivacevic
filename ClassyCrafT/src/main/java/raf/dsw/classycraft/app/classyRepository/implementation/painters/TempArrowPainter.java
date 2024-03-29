package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TempArrowPainter extends ElementPainter
{
    @Getter
    private int endX, endY;
    public TempArrowPainter(DiagramElement element, int x, int y) {
        super(element, x, y);
        this.endX = x;
        this.endY = y;
    }

    @Override
    public ElementPainter copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void paint(Graphics2D g) {
        // Draw dashed gray line
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g.setStroke(dashed);
        g.setColor(Color.GRAY);
        g.drawLine(getX(), getY(), endX, endY);
    }

    @Override
    public Rectangle getBoundingBox() {

        return new Rectangle(getX(),getY(),Math.abs(endX - getX()),Math.abs(endY - getY()));
    }

    public void updateEndPos(int x, int y)
    {
        this.endX = x;
        this.endY = y;
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
