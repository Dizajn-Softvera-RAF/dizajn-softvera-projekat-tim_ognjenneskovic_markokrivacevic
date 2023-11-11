package raf.dsw.classycraft.app.tree;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.tree.controller.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.tree.view.ClassyTreeCellRenderer;
import raf.dsw.classycraft.app.tree.view.ClassyTreeView;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

public class ClassyTree
{
    private ClassyTreeView treeView = null;
    public ClassyNode createNode(ClassyNodeComposite parent)
    {
        if(parent instanceof ProjectExplorer)
            return new Project("New Project", "Test Path",
                    "Test project", parent);
        else if(parent instanceof Project)
            return new Package("New Package", parent);
        else if(parent instanceof Package)
            return new Diagram("New Diagram", parent);
        else
            throw new RuntimeException("Invalid parent type");
    }
    public void addChild(ClassyTreeItem parent, ClassyNode child) throws Exception {
        if(parent.getNode() instanceof ClassyNodeComposite) {
            parent.add(new ClassyTreeItem(child));
            ((ClassyNodeComposite) parent.getNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        else
            throw new Exception("Unable to add child to this node");
    }
    public ClassyTreeView generateTree()
    {
        var root = new ClassyTreeItem(new ProjectExplorer());
        var treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);

        return treeView;
    }
    public ClassyTreeItem getSelectedNode()
    {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }
    public void addOnSelectionListener(TreeSelectionListener listener)
    {
        treeView.addTreeSelectionListener(listener);
    }
}
