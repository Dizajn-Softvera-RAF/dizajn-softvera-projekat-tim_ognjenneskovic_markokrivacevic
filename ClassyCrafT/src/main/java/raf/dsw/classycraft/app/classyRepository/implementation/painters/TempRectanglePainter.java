package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;

public class TempRectanglePainter extends ElementPainter
{
    int width, height;
    public TempRectanglePainter(DiagramElement element, int x, int y, int width, int height) {
        super(element, x, y);
        this.width = width;
        this.height = height;
    }
    public TempRectanglePainter(DiagramElement element, int x, int y) {
        super(element, x, y);
        this.width = 100;
        this.height = 100;
    }

    @Override
    public ElementPainter copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void paint(Graphics2D g) {
        // Draw dashed gray rectangle
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g.setStroke(dashed);
        g.setColor(Color.GRAY);
        g.drawRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x,y,width,height);
    }

    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
