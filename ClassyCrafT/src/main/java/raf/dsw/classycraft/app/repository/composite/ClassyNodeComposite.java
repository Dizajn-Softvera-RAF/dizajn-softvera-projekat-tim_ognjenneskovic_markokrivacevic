package raf.dsw.classycraft.app.repository.composite;


import lombok.Getter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode
{

    private final List<IClassyNodeListener> childListeners;
    @Getter
    private final List<ClassyNode> children;
    public ClassyNodeComposite(String name, ClassyNodeComposite parent)
    {
        super(name, parent);
        childListeners = new ArrayList<>();
        children = new ArrayList<>();
    }
    public void addChild(ClassyNode child)
    {
        children.add(child);
        onChange();
    }
    public void removeChild(ClassyNode child)
    {
        children.remove(child);
        onChange();
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
