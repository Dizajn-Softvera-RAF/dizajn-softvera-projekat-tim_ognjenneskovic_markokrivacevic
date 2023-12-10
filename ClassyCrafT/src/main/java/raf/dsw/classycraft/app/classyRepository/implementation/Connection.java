package raf.dsw.classycraft.app.classyRepository.implementation;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;

import java.awt.*;

public abstract class Connection extends DiagramElement
{
    protected Interclass nodeFrom;
    protected Interclass nodeTo;
    public Connection(ClassyNode parent, Color color, int strokeWidth, Interclass nodeFrom, Interclass nodeTo)
    {
        super("Connection", parent, color, strokeWidth);
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
    }
}
