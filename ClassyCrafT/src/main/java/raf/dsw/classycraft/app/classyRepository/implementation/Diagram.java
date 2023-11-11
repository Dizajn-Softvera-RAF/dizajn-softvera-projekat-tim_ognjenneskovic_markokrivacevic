package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;

public class Diagram extends ClassyNode {

    public Diagram(String name, ClassyNode parent) {
        super("Diagram" + " " + " ", parent);
    }
}
