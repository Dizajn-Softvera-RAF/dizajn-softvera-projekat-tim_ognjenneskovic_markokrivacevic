package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class CompositionPainter extends ElementPainter {

    @Getter
    private int endX,endY;

    public CompositionPainter(DiagramElement element,int x,int y){
        super(element,x,y);
        this.endX=x;
        this.endY=y;
    }

    @Override
    public void paint(Graphics2D g) {
        paintLineWithSquare(g, x, y, endX,endY);
    }

    public static void paintLineWithSquare(Graphics2D g, int x, int y, int endX, int endY) {
        // Draw line
        Stroke stroke = new BasicStroke(3,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL,0,new float[]{9},0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawLine(x, y, endX, endY);

        // Draw square at the end
        int squareSize = 10;
        g.fillRect(endX - squareSize / 2, endY - squareSize / 2, squareSize, squareSize);
    }

    public static void paintLineWithArrow(Graphics2D g, int x, int y, int endX, int endY) {
        // Draw line
        g.setColor(Color.BLACK);
        g.drawLine(x, y, endX, endY);

        // Draw arrow at the end
        double angle = Math.atan2(endY - y, endX - x);
        drawArrowhead(g, endX, endY, angle);
    }

    private static void drawArrowhead(Graphics2D g, int x, int y, double angle) {
        int arrowSize = 10;

        AffineTransform tx = new AffineTransform();
        tx.translate(x, y);
        tx.rotate(angle);

        Path2D path = new Path2D.Double();
        path.moveTo(-arrowSize / 2, -arrowSize / 2);
        path.lineTo(arrowSize / 2, -arrowSize / 2);
        path.lineTo(0, arrowSize / 2);
        path.closePath();

        g.fill(tx.createTransformedShape(path));
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x,y,Math.abs(endX-x),Math.abs(endY-y));
    }

    public void updateEndPos(int x,int y){
        this.endX=x;
        this.endY=y;
    }

    @Override
    public void applyTransformToPoints(AffineTransform transform) {
        super.applyTransformToPoints(transform);
        var point = new Point(endX,endY);
        transform.transform(point,point);
        endX = point.x;
        endY = point.y;
    }
}
