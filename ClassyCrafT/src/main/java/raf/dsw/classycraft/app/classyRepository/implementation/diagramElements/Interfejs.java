package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;

import java.awt.*;
import java.util.ArrayList;

public class Interfejs extends Interclass
{
    private final ArrayList<Metod> methods = new ArrayList<>();
    public Interfejs(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent, color, strokeWidth);
    }
}
