package raf.dsw.classycraft.app.classyRepository.implementation.painters;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;

import java.awt.*;

public class InterClassPainter extends ElementPainter {

    public InterClassPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        // Paint test rectangle at random pos with size 100x100
        g.setColor(Color.BLACK);
        int x = (int) (Math.random() * 250);
        int y = (int) (Math.random() * 250);
        g.drawRect(x, y, 100, 100);


    }
}
