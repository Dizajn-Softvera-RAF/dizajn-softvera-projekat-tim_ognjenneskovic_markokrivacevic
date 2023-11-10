package raf.dsw.classycraft.app.repository.composite;


import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode
{

    private final List<IClassyNodeListener> childListeners;
    public ClassyNodeComposite(String name, ClassyNodeComposite parent)
    {
        super(name, parent);
        this.treeNode = new DefaultMutableTreeNode(this);
        childListeners = new ArrayList<>();
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
    protected void onChildChange()
    {
        for(var listener : childListeners)
            listener.onNodeChanged(this);
        if(parent != null)
            parent.onChildChange();
    }
    public void addOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.add(listener);
    }
    public void removeOnChildChangeListener(IClassyNodeListener listener) {
        childListeners.remove(listener);
    }

    @Override
    protected void onChange() {
        super.onChange();
        if(parent != null)
            parent.onChildChange();
    }
}
