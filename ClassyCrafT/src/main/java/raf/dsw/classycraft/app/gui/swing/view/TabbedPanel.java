package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class TabbedPanel extends JTabbedPane implements TreeSelectionListener, Subscriber {
    private Package selectedPackage;

    public TabbedPanel(){
        selectedPackage = null;
    }
    public void openTabs(Package pckg)
    {
        removeAll();
        for (var child : pckg.getChildren())
        {
            if (child instanceof Diagram) {
                var diagram = (Diagram) child;
                this.add(diagram.getName(), new DiagramView());
            }
        }
        setListeners(pckg);
    }

    /**
     * Removes listeners from old package and adds them to new package
     * Sets selectedPackage to new package
     */
    private void setListeners(Package newPackage)
    {
        // Remove listeners from old package
        if(selectedPackage != null)
        {
            selectedPackage.removeSubscriber(this);
        }

        // Add listeners to new package
        selectedPackage = newPackage;
        selectedPackage.addSubscriber(this);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        var node = treeItemSelected.getClassyNode();
        if(node instanceof Package)
        {
            var pckg = (Package) node;
            openTabs(pckg);
        }
    }

    /**
     * Called when a Diagram in selected package changes or a package changes
     */
    @Override
    public void update(Object notification) {
        if(notification instanceof Package) {
            var pckg = (Package) notification;
            openTabs(pckg);
        }
        else
        {
            var diagram = (Diagram) notification;
            var parentPackage = (Package) diagram.getParent();
            openTabs(parentPackage);
        }
    }
}
