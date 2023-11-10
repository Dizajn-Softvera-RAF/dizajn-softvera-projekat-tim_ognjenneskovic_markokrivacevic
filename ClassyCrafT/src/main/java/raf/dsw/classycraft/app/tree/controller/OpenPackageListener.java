package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.DiagramTabs;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Package;

public class OpenPackageListener implements IClassyNodeListener
{
    private DiagramTabs diagramTabs;
    private Package currentPackage; // Currently selected package
    private final ModifiedPackageListener currentPackageModifiedListener; // Listener for modifications
    // of currently selected package
    public OpenPackageListener(DiagramTabs diagramTabs)
    {
        this.diagramTabs = diagramTabs;
        currentPackageModifiedListener = new ModifiedPackageListener(diagramTabs);
    }

    /**
     * Called when a new package is selected
     * @param node
     */
    @Override
    public void onNodeChanged(ClassyNode node) {
        if(node instanceof Package) {
            // Move the listener to the new package
            if (currentPackage != null) {
                currentPackage.removeOnChangeListener(currentPackageModifiedListener);
                currentPackage.removeOnChildChangeListener(currentPackageModifiedListener);
            }
            currentPackage = (Package) node;
            currentPackage.addOnChangeListener(currentPackageModifiedListener);
            currentPackage.addOnChildChangeListener(currentPackageModifiedListener);

            // Open the tabs for the new package
            diagramTabs.OpenTabs((Package) node);
        }
        else
        {
            //TODO: Throw exception
        }
    }
}
