package raf.dsw.classycraft.app.classyRepository;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ClassyNodeImplementation implements ClassyRepository {

    private ProjectExplorer projectExplorer;


    public ClassyNodeImplementation() {
        projectExplorer = new ProjectExplorer("My Project explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode child) {

    }

    @Override
    public void deleteChild(ClassyNodeComposite parent, ClassyNode child) {

    }

}
