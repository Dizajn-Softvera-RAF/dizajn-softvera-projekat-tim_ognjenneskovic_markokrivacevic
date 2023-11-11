package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.DiagramTabs;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class PackageSelectListener implements TreeSelectionListener
{
    private DiagramTabs diagramTabs;
    private Package currentPackage; // Currently selected package
    private final PackageModifiedListener currentPackageModifiedListener; // Listener for modifications
    // of currently selected package
    public PackageSelectListener(DiagramTabs diagramTabs)
    {
        this.diagramTabs = diagramTabs;
        currentPackageModifiedListener = new PackageModifiedListener(diagramTabs);
    }
    /**
     * Called when a new node is selected in the tree
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        var path = e.getPath();
        var selectedItem = (ClassyTreeItem) path.getLastPathComponent();
        var node = selectedItem.getNode();

        // Remove listener from old package
        if (currentPackage != null) {
            currentPackage.removeOnChangeListener(currentPackageModifiedListener);
            currentPackage.removeOnChildChangeListener(currentPackageModifiedListener);
        }

        currentPackage = getSelectedPackage(node);

        // Add listener and notify listener that node has changed (possibly)
        if (currentPackage != null) {
            currentPackage.addOnChangeListener(currentPackageModifiedListener);
            currentPackage.addOnChildChangeListener(currentPackageModifiedListener);
            currentPackageModifiedListener.onNodeChanged(currentPackage);
        }
    }
    private Package getSelectedPackage(ClassyNode node)
    {
        if(node instanceof Package)
            return (Package) node;
        else if(node instanceof Diagram) {
            var parent = node.getParent();
            if(parent instanceof Package)
                return (Package) node.getParent();
            else
                throw new RuntimeException("Selected a node that doesn't belong to a valid tree: " +
                        "Diagram must have a Package as a parent");
        }
        else
            return null;
    }
}
