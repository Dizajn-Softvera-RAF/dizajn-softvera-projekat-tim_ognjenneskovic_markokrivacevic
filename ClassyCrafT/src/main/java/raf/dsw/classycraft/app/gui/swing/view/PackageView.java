package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;

@Getter
@Setter
public class PackageView extends JPanel implements TreeSelectionListener, Subscriber
{
    private Package selectedPackage;
    private JTabbedPane tabbedPane;

    public PackageView()
    {
        selectedPackage = null;
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }
    public void openTabs(Package pckg)
    {
        tabbedPane.removeAll();
        for (var child : pckg.getChildren())
        {
            if (child instanceof Diagram) {
                var diagram = (Diagram) child;
                tabbedPane.add(diagram.getName(), new DiagramView());
            }
        }
        tabbedPane.addChangeListener(e -> {
            var selected = tabbedPane.getSelectedComponent();
            if(selected instanceof DiagramView)
            {
                var diagramView = (DiagramView) selected;
                diagramView.paint();
            }
        });
        setListeners(pckg);
    }
    /**
     * Removes listeners from old package and adds them to new package
     * Sets selectedPackage to new package
     * Removes listener from old parent project and adds it to new parent project
     */
    private void setListeners(Package newPackage)
    {
        // Remove listeners from old package and project
        if(selectedPackage != null)
        {
            selectedPackage.removeSubscriber(this);
            var oldParentProject = getParentProject(selectedPackage);
            if(oldParentProject != null)
                oldParentProject.removeSubscriber(this);
        }

        // Add listeners to new package
        selectedPackage = newPackage;
        selectedPackage.addSubscriber(this);
        // Add listener to new parent project
        var newParentProject = getParentProject(selectedPackage);
        if(newParentProject != null)
            newParentProject.addSubscriber(this);
    }
    /**
     * Called when a node in the tree is selected
     */
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

        var nodeEvent = (NodeChangeEvent) notification;
        var node = nodeEvent.getNode();

        if(node instanceof Package) // Handle package deletion
        {
            var pckg = (Package) node;
            openTabs(pckg);
            if(nodeEvent.getType() == NodeEventType.NODE_REMOVED)
                tabbedPane.removeAll();
        }
        else if(node instanceof Diagram) // Handle diagram deletion or changes
        {
            var diagram = (Diagram) node;
            var parentPackage = (Package) diagram.getParent();
            openTabs(parentPackage);
        }
        else if(node instanceof Project) // Handle project deletion
        {
            if(nodeEvent.getType() == NodeEventType.NODE_REMOVED)
                tabbedPane.removeAll();
        }
    }
    private Project getParentProject(ClassyNode node)
    {
        while(node != null && !(node instanceof Project))
            node = node.getParent();

        if(node == null)
            return null;
        else
            return (Project) node;
    }
}
