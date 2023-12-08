package raf.dsw.classycraft.app.classyRepository.composite;

import java.awt.*;

public abstract class ElementPainter
{
    protected DiagramElement element;
    public ElementPainter(DiagramElement element)
    {
        this.element = element;
    }
    public abstract void paint(Graphics g);
}
