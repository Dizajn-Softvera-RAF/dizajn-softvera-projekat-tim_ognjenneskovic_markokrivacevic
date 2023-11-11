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
    private ClassyTreeItem selectedNode = null;
    private final List<IClassyNodeListener> selectionListeners;
    public ClassyTree()
    {
        selectionListeners = new ArrayList<>();
    }
    private void setSelectedNode(ClassyTreeItem newNode)
    {
        selectedNode = newNode;
        for(var listener : selectionListeners)
            listener.onNodeChanged(newNode.getNode());
    }
    public ClassyNode createNode(NodeType type, String name, ClassyNodeComposite parent)
    {
        switch (type)
        {
            case PACKAGE:
                return new Package(name, parent);
            case DIAGRAM:
                return new Diagram(name, parent);
            default:
                return null;
        }
    }
    public Project createNode(NodeType type, String name, String author, String path, ClassyNodeComposite parent)
    {
        return new Project(author, path, name, parent);
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

        treeView.addTreeSelectionListener(e -> {
            var selectedObj = treeView.getLastSelectedPathComponent();
            if (selectedObj instanceof ClassyTreeItem) {
                var treeItem = (ClassyTreeItem) treeView.getLastSelectedPathComponent();
                setSelectedNode(treeItem);
            }
        });

        return treeView;
    }
    public ClassyTreeItem getSelectedNode()
    {
        return selectedNode;
    }
    public void addOnSelectionListener(IClassyNodeListener listener)
    {
        selectionListeners.add(listener);
    }
    public void addOnSelectionListener(TreeSelectionListener listener)
    {
        treeView.addTreeSelectionListener(listener);
    }
}
