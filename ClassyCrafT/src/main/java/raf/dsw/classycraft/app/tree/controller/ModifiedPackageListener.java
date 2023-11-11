package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.DiagramTabs;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Package;

public class ModifiedPackageListener implements IClassyNodeListener
{
    private DiagramTabs diagramTabs;
    public ModifiedPackageListener(DiagramTabs diagramTabs)
    {
        this.diagramTabs = diagramTabs;
    }
    @Override
    public void onNodeChanged(ClassyNode node) {
        if(node instanceof Package) {
            var packageNode = (Package) node;
            diagramTabs.OpenTabs(packageNode);
        }
        else
        {
            //TODO: Throw exception
        }
    }
}
