package raf.dsw.classycraft.app.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;

public class ClassyTree
{
    private static ClassyTree instance = null;
    private ProjectExplorer root;
    private JTree jtreeInstance = null;
    private ClassyNode selectedNode = null;
    private ClassyTree()
    {
        root = new ProjectExplorer("Project Explorer");
    }
    public static ClassyTree getInstance()
    {
        if(instance == null)
            instance = new ClassyTree();
        return instance;
    }
    public ClassyNode createNode(NodeType type, String name)
    {
        switch (type)
        {
            case PACKAGE:
                return new Package(name);
            case DIAGRAM:
                return new Diagram(name);
            default:
                return null;
        }
    }
    public Project createNode(NodeType type, String name, String author, String path)
    {
        return new Project(author, path, name);
    }
    public void addChild(ClassyNode parent, ClassyNode child) throws Exception {
        if(parent instanceof ClassyNodeComposite)
            ((ClassyNodeComposite)parent).addChild(child);
        else
            throw new Exception("Unable to add child to this node");
    }
    public void generateTree(JPanel frame)
    {
        if (jtreeInstance != null)
            frame.remove(jtreeInstance);
        var tree = new JTree(root.getTreeNode());

        var textField = new JTextField();
        var editor = new DefaultCellEditor(textField);

        tree.setEditable(true);
        tree.setCellEditor(editor);
        editor.addCellEditorListener(new ClassyTreeCellEditorListener(textField));

        frame.add(tree);
        frame.revalidate();
        frame.repaint();
        jtreeInstance = tree;
        tree.addTreeSelectionListener(e -> {
            var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            if(node != null)
                selectedNode = (ClassyNode) node.getUserObject();
        });
    }
    public ClassyNode getSelectedNode()
    {
        return selectedNode;
    }
}
