package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.gui.swing.view.DiagramTabs;

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
