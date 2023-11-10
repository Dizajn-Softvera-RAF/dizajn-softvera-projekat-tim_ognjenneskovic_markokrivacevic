package raf.dsw.classycraft.app.tree;


import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite implements ClassyNode
{
    private DefaultMutableTreeNode treeNode;
    protected String name;
    private List<IClassyNodeListener> listeners, childListeners;
    protected ClassyNodeComposite parent;
    public ClassyNodeComposite(String name, ClassyNodeComposite parent)
    {
        this.name = name;
        this.treeNode = new DefaultMutableTreeNode(this);
        listeners = new ArrayList<>();
        childListeners = new ArrayList<>();
        this.parent = parent;
    }
    public void addChild(ClassyNode child)
    {
        treeNode.add(child.getTreeNode());
        onChange();
    }
    public void removeChild(ClassyNode child)
    {
        treeNode.remove(child.getTreeNode());
        onChange();
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
    protected void onChange()
    {
        for(var listener : listeners)
            listener.onNodeChanged(this);
        if (parent != null)
            parent.onChildChange();
    }
    protected void notifyChildListeners()
    {
        for(var listener : childListeners)
            listener.onNodeChanged(this);
    }
    protected void onChildChange()
    {
        notifyChildListeners();
        if(parent != null)
            parent.onChildChange();
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
        onChange();
    }
    @Override
    public DefaultMutableTreeNode getTreeNode() {
        return treeNode;
    }
    public String toString()
    {
        return name;
    }

    @Override
    public void addOnChangeListener(IClassyNodeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeOnChangeListener(IClassyNodeListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void addOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.add(listener);
    }

    @Override
    public void removeOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.remove(listener);
    }
}
