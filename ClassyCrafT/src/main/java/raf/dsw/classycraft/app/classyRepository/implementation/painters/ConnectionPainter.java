package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;

public class ConnectionPainter extends ElementPainter {
    private int endX, endY;
    public ConnectionPainter(DiagramElement element, int startX, int startY, int endX, int endY) {
        super(element, startX, startY);
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public Rectangle getBoundingBox(Graphics g) {
        return null;
    }
}
