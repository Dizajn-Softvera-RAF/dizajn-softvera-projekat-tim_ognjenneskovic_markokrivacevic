package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;

public class TempRectanglePainter extends ElementPainter
{
    int width, height;
    public TempRectanglePainter(DiagramElement element, int x, int y) {
        super(element, x, y);
        width = 100;
        height = 100;
    }

    @Override
    public void paint(Graphics g) {
        // Draw dashed gray rectangle
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(Color.GRAY);
        g2d.drawRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingBox(Graphics g) {
        return new Rectangle(x,y,width,height);
    }

    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
