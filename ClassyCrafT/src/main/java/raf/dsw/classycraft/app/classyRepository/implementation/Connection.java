package raf.dsw.classycraft.app.classyRepository.implementation;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;

import java.awt.*;

public abstract class Connection extends DiagramElement
{
    @Getter
    @Setter
    protected Interclass nodeFrom;
    @Getter
    @Setter
    protected Interclass nodeTo;
    @Getter @Setter
    private int endX, endY;
    public Connection(ClassyNode parent, Color color, int strokeWidth,
                      Interclass nodeFrom, Interclass nodeTo, int x, int y, int endX, int endY)
    {
        super("Connection", parent, color, strokeWidth, x, y);
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.endX = endX;
        this.endY = endY;
    }
}
