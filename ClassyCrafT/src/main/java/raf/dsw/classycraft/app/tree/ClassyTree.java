package raf.dsw.classycraft.app.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import java.util.ArrayList;
import java.util.List;

public class ClassyTree
{
    private static ClassyTree instance = null;
    private ProjectExplorer root;
    private JTree jtreeInstance = null;
    private ClassyNode selectedNode = null;
    private List<IClassyNodeListener> selectionListeners;
    private ClassyTree()
    {
        root = new ProjectExplorer();
        selectionListeners = new ArrayList<>();
    }
    private void setSelectedNode(ClassyNode newNode)
    {
        selectedNode = newNode;
        for(var listener : selectionListeners)
            listener.onNodeChanged(newNode);
    }
    public static ClassyTree getInstance()
    {
        if(instance == null)
            instance = new ClassyTree();
        return instance;
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
    public void addChild(ClassyNode parent, ClassyNode child) throws Exception {
        if(parent instanceof ClassyNodeComposite) {
            ((ClassyNodeComposite) parent).addChild(child);
            SwingUtilities.updateComponentTreeUI(jtreeInstance);
        }
        else
            throw new Exception("Unable to add child to this node");
    }
    public void generateTree(JPanel frame)
    {
        if (jtreeInstance != null)
            frame.remove(jtreeInstance);
        var tree = new JTree(root.getTreeNode());
        tree.setModel(new DefaultTreeModel(root.getTreeNode()));

        var renderer = new ClassyTreeCellRenderer();
        tree.setCellEditor(new ClassyTreeCellEditor(tree, renderer));
        tree.setCellRenderer(renderer);
        tree.setEditable(true);

        //editor.addCellEditorListener(new ClassyTreeCellEditorListener(textField));

        frame.add(tree);
        frame.revalidate();
        frame.repaint();
        jtreeInstance = tree;

        tree.addTreeSelectionListener(e -> {
            var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if(node != null)
                setSelectedNode((ClassyNode) node.getUserObject());
        });

    }
    public ClassyNode getSelectedNode()
    {
        return selectedNode;
    }
    public void addOnSelectionListener(IClassyNodeListener listener)
    {
        selectionListeners.add(listener);
    }
}
