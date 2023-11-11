package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class Package extends ClassyNodeComposite
{
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }
}
