package raf.dsw.classycraft.app.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public interface ClassyNode
{
    String getName();
    void setName(String name);
    DefaultMutableTreeNode getTreeNode();
    void addOnChangeListener(IClassyNodeListener listener);
    void removeOnChangeListener(IClassyNodeListener listener);
    void addOnChildChangeListener(IClassyNodeListener listener);
    void removeOnChildChangeListener(IClassyNodeListener listener);
}
