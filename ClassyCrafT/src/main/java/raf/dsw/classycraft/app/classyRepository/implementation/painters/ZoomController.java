package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.DiagramViewManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class ZoomController
{
    private AffineTransform currentTransform;
    public ZoomController()
    {
        currentTransform = new AffineTransform();
    }
    public void handleClick(int x, int y, DiagramView diagramView, double scaleFactor)
    {
        var cx = diagramView.getWidth() / 2;    // Center of screen x
        var cy = diagramView.getHeight() / 2;   // Center of screen y
        // Move (x,y) to center of screen
        var dx = cx - x;
        var dy = cy - y;
        var translateTransform = new AffineTransform();
        translateTransform.translate(dx, dy);

        var zoomTransform = new AffineTransform();
        // Scale around (cx,cy), note that java combines transforms in reverse order
        // Translate back to (cx,cy)
        zoomTransform.translate(cx, cy);
        // Scale around (0,0)
        zoomTransform.scale(scaleFactor, scaleFactor);
        // Translate to (cx,cy)
        zoomTransform.translate(-cx, -cy);
        // Combine with translate transform
        zoomTransform.concatenate(translateTransform);

        zoomTransform.concatenate(currentTransform);
        currentTransform = zoomTransform;
    }
    public Point getOriginalPoint(Point p) {
        var inverseTransform = new AffineTransform();
        try {
            inverseTransform = currentTransform.createInverse();
        } catch (NoninvertibleTransformException e) {
            return null;
        }

        var point = new Point(p.x, p.y);
        inverseTransform.transform(point, point);
        return point;
    }
    public void applyTransform(Graphics2D g)
    {
        g.transform(currentTransform);
    }
    public void resetTransform()
    {
        currentTransform = new AffineTransform();
    }
    public void zoomToFit(DiagramView diagramView)
    {
        // Construct bounding rectangle that includes all shapes
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(var painter: diagramView.getPainters())
        {
            var bb = painter.getBoundingBox();
            if(bb.x < minX)
                minX = bb.x;
            if(bb.y < minY)
                minY = bb.y;
            if(bb.x + bb.width > maxX)
                maxX = bb.x + bb.width;
            if(bb.y + bb.height > maxY)
                maxY = bb.y + bb.height;
        }
        // Add padding
        var padding = 10;
        minX -= padding;
        minY -= padding;
        maxX += padding;
        maxY += padding;
        // Find a scale factor that will either scale horizontally or vertically to fit
        var screenWidth = diagramView.getWidth();
        var screenHeight = diagramView.getHeight();
        var horizontalScaleFactor = (double) screenWidth / (maxX - minX);
        var verticalScaleFactor = (double) screenHeight / (maxY - minY);
        var scaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);
        // Center the diagram (same as if there was a click in the middle of the bounding box)
        var cx = minX + (maxX - minX) / 2;
        var cy = minY + (maxY - minY) / 2;
        handleClick(cx, cy, diagramView, scaleFactor);
        diagramView.paint();
    }
}
