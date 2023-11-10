package raf.dsw.classycraft.app.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyLeaf implements ClassyNode
{
    protected String name;
    private DefaultMutableTreeNode treeNode;
    protected List<IClassyNodeListener> listeners, childListeners;
    protected ClassyNodeComposite parent;
    public ClassyLeaf(String name, ClassyNodeComposite parent)
    {
        this.name = name;
        this.treeNode = new DefaultMutableTreeNode(this);
        listeners = new ArrayList<>();
        childListeners = new ArrayList<>();
        this.parent = parent;
    }
    protected void onChange()
    {
        for(var listener : listeners)
            listener.onNodeChanged(this);
        if (parent != null)
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
    @Override public void addOnChangeListener(IClassyNodeListener listener) {
        listeners.add(listener);
    }
    @Override public void removeOnChangeListener(IClassyNodeListener listener) {
        listeners.remove(listener);
    }
    @Override public void addOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.add(listener);
    }
    @Override public void removeOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.remove(listener);
    }

}
