package raf.dsw.classycraft.app.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public interface ClassyNode
{
    String getName();
    void setName(String name);
    DefaultMutableTreeNode getTreeNode();
}
