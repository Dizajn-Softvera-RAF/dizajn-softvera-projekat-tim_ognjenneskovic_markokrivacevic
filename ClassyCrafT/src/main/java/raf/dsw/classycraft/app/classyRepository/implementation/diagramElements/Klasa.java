package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;

import java.awt.*;
import java.util.ArrayList;

public class Klasa extends Interclass
{
    public Klasa(String name, ClassyNode parent, Color color, int strokeWidth, int x, int y) {
        super(name, parent, color, strokeWidth, x, y);
    }

    @Override
    public DiagramElement copy() {
        return new Klasa(this);
    }

    public Klasa(Klasa other)
    {
        super(other);
    }
}
