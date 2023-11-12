package raf.dsw.classycraft.app.classyRepository;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.gui.swing.factory.FactoryUtils;
import raf.dsw.classycraft.app.gui.swing.factory.NodeFactory;

public class ClassyRepositoryImplementation implements ClassyRepository {

    private final ProjectExplorer projectExplorer;
    private final FactoryUtils factoryUtils;


    public ClassyRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project explorer");
        factoryUtils = new FactoryUtils();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public ClassyNode createChild(ClassyNode parent) {
        NodeFactory nf = factoryUtils.getNodeFactory(parent);
        return nf.createNode((ClassyNodeComposite) parent);
    }

}
