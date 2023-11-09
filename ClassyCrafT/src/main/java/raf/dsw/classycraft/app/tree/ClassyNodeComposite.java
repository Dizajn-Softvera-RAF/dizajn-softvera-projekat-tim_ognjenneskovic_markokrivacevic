package raf.dsw.classycraft.app.tree;


import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite implements ClassyNode
{
    private DefaultMutableTreeNode treeNode;
    protected String name;
    public ClassyNodeComposite(String name)
    {
        this.name = name;
        this.treeNode = new DefaultMutableTreeNode(this);
    }
    public void addChild(ClassyNode child)
    {
        treeNode.add(child.getTreeNode());
    }
    public void removeChild(ClassyNode child)
    {
        treeNode.remove(child.getTreeNode());
    }
    public List<ClassyNode> getChildren()
    {
        var children = new ArrayList<ClassyNode>();
        for(int i = 0; i < treeNode.getChildCount(); i++)
        {
            var node = (DefaultMutableTreeNode)treeNode.getChildAt(i);
            children.add((ClassyNode) node.getUserObject());
        }
        return children;
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
