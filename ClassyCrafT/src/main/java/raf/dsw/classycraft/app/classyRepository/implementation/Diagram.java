package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class Diagram extends ClassyNodeComposite {

    public Diagram(String name, ClassyNode parent) {
        super("Diagram" + " " + " ", parent);
    }
}
