package raf.dsw.classycraft.app.gui.swing.factory;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

@Setter
@Getter
public class FactoryUtils {

    private ProjectExplorerFactory projectExplorerFactory;
    private ProjectFactory projectFactory;
    private DiagramFactory diagramFactory;
    private PackageFactory packageFactory;

    private DiagramElementFactory diagramElementFactory;

    public FactoryUtils(){
        initialiseActions();
    }
    private void initialiseActions(){
        projectExplorerFactory = new ProjectExplorerFactory();
        projectFactory = new ProjectFactory();
        packageFactory = new PackageFactory();
        diagramFactory = new DiagramFactory();
        diagramElementFactory = new DiagramElementFactory();
    }

    public NodeFactory getNodeFactory(ClassyNode node){
        if((node instanceof Package) && MainFrame.getInstance().getActionManager().
                getNewPackageAction().isCurrentAction())
            return packageFactory;

        if(node instanceof Package){
            return diagramFactory;
        }else if(node instanceof Project){
            return packageFactory;
        }else if(node instanceof ProjectExplorer){
            return projectFactory;
        }else if(node instanceof Diagram) {
            return diagramElementFactory;
        }else
            return null;
    }

}
