package raf.dsw.classycraft.app.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class ClassyLeaf implements ClassyNode
{
    protected String name;
    private DefaultMutableTreeNode treeNode;
    public ClassyLeaf(String name)
    {
        this.name = name;
        this.treeNode = new DefaultMutableTreeNode(this);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }
    public String toString()
    {
        return name;
    }

}
