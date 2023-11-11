package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyLeaf;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class Diagram extends ClassyLeaf
{
    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }
}
