package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
    public void applyTransform(Graphics2D g)
    {
        g.transform(currentTransform);
    }
    public void resetTransform()
    {
        currentTransform = new AffineTransform();
    }
}
