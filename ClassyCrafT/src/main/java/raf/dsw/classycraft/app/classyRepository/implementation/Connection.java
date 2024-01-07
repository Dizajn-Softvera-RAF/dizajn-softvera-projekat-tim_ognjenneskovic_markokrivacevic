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
    public Connection(ClassyNode parent, Color color, int strokeWidth, Interclass nodeFrom, Interclass nodeTo)
    {
        super("Connection", parent, color, strokeWidth);
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
    }
}
