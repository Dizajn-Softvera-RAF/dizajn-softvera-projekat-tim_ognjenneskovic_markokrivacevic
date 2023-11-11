package raf.dsw.classycraft.app.gui.swing.factory;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;

public class ProjectFactory extends NodeFactory{

    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        return new Project("Project",parent);
    }
}
