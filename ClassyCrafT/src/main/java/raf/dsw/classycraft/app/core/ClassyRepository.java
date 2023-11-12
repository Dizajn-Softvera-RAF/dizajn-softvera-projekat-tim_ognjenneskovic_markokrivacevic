package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.factory.NodeFactory;

public interface ClassyRepository {

    ProjectExplorer getProjectExplorer();

    public ClassyNode createChild(ClassyNode parent);
}
