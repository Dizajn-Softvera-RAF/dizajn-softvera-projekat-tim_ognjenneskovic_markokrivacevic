package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Agregacija;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Kompozicija;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Zavisnost;
import raf.dsw.classycraft.app.geometry.linalg.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ArrowDrawer
{
    private double arrowSize;
    public ArrowDrawer(double arrowSize)
    {
        this.arrowSize = arrowSize;
    }

    public <T extends Connection> void drawArrow(Graphics2D g, Class<T> connectionClass,
                                                 int x, int y, int endX, int endY, Color lineColor)
    {
        Point arrowHeadTip = new Point(0, (int)arrowSize);
        var arrowHeadPath = new Path2D.Double();
        if(connectionClass == Zavisnost.class)
        {
            // Plain arrow, not closed (---->)
            arrowHeadPath.moveTo(-arrowSize/2, 0);
            arrowHeadPath.lineTo(0, arrowSize);
            arrowHeadPath.lineTo(arrowSize/2, 0);
        }
        else if(connectionClass == Agregacija.class || connectionClass == Kompozicija.class)
        {
            // Closed parallelogram (----<>)
            arrowHeadPath.moveTo(-arrowSize/2, 0);
            arrowHeadPath.lineTo(0, arrowSize);
            arrowHeadPath.lineTo(arrowSize/2, 0);
            arrowHeadPath.lineTo(0, -arrowSize);
            arrowHeadPath.closePath();
        }
        else if(connectionClass == Generalizacija.class)
        {
            // Closed rectangle ----|>
            arrowHeadPath.moveTo(-arrowSize/2, 0);
            arrowHeadPath.lineTo(0, arrowSize);
            arrowHeadPath.lineTo(arrowSize/2, 0);
            arrowHeadPath.closePath();
        }
        // Compute signed angle between direction vector and arrow head tip vector
        var dirVec = new Vector(new Point(x, y), new Point(endX, endY));
        var tipVec = new Vector(new Point(0,0), arrowHeadTip);
        double dot = dirVec.dot(tipVec);
        double det = dirVec.getX() * tipVec.getY() - dirVec.getY() * tipVec.getX();
        double angle = -Math.atan2(det, dot);

        var tx = new AffineTransform();
        tx.rotate(angle);
        // Get position of tip after rotation
        Point rotatedTip = new Point();
        tx.transform(arrowHeadTip, rotatedTip);

        // Translate arrow head to the end of the line
        double dx = endX - rotatedTip.getX();
        double dy = endY - rotatedTip.getY();
        // Compose in correct order
        var tx2 = new AffineTransform();
        tx2.translate(dx, dy);
        tx2.rotate(angle);

        // Draw line
        g.setColor(lineColor);
        g.drawLine(x, y, endX, endY);
        // Draw arrow head
        if(connectionClass == Agregacija.class || connectionClass == Generalizacija.class)
        {
            // Fill white
            g.setColor(Color.WHITE);
            g.fill(tx2.createTransformedShape(arrowHeadPath));
        }
        else if(connectionClass == Kompozicija.class)
        {
            // Fill black
            g.setColor(lineColor);
            g.fill(tx2.createTransformedShape(arrowHeadPath));
        }
        // Draw outline
        g.setColor(lineColor);
        g.draw(tx2.createTransformedShape(arrowHeadPath));

    }
}
