package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;

import java.awt.*;

public class TempElement extends DiagramElement
{
    public TempElement() {
        super("temp", null, null, 0);
    }

    @Override
    public DiagramElement copy() {
        return new TempElement();
    }
}
