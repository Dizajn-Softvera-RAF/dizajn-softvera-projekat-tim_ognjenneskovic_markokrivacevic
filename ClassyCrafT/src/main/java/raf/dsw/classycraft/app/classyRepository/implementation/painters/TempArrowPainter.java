package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;

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
    public void paint(Graphics g) {
        // Draw dashed gray line
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(Color.GRAY);
        g2d.drawLine(x, y, endX, endY);
    }

    @Override
    public Rectangle getBoundingBox() {

        return new Rectangle(x,y,Math.abs(endX - x),Math.abs(endY - y));
    }

    public void updateEndPos(int x, int y)
    {
        this.endX = x;
        this.endY = y;
    }

}
