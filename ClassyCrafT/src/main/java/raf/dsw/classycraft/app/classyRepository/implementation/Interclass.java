package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;

import java.awt.*;

public abstract class Interclass extends DiagramElement
{

    public Interclass(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent, color, strokeWidth);
    }
}
