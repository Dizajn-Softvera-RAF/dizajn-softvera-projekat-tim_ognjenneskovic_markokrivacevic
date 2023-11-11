package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class ProjectSelectListener implements TreeSelectionListener
{
    private ProjectModifiedListener modifiedListener;
    private Project selectedProject;
    public ProjectSelectListener()
    {
        this.modifiedListener = new ProjectModifiedListener();
        selectedProject = null;
    }

    /**
     * Called when a new node is selected in the tree
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        var path = e.getPath();
        var selectedItem = (ClassyTreeItem) path.getLastPathComponent();

        // Remove listener from old project
        if (selectedProject != null)
            selectedProject.removeOnChangeListener(modifiedListener);

        // Find the new project
        var project = getSelectedProject(selectedItem);
        selectedProject = project;

        // Add listener and notify listener that node has changed (possibly)
        if(selectedProject != null) {
            project.addOnChangeListener(modifiedListener);
            modifiedListener.onNodeChanged(project);
        }
    }

    /**
     * Finds the project that the selected node belongs to
     */
    private Project getSelectedProject(ClassyTreeItem selectedNode)
    {
        var node = selectedNode.getNode();

        // Climb up the tree until a project is found or the root is reached
        while (node != null && !(node instanceof Project))
            node = node.getParent();

        if(!(selectedNode.getNode() instanceof ProjectExplorer) && node == null)
            throw new RuntimeException("Selected a node that doesn't belong to a valid tree: " +
                    "node must have a project as a parent");

        if (node == null)
            return null;
        return (Project) node;
    }
}
