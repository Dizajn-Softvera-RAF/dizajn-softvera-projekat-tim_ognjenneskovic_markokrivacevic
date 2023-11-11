package raf.dsw.classycraft.app.repository.composite;

import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public abstract class ClassyNode
{
    @Getter
    private String name;
    public void setName(String name) {
        this.name = name;
        onChange();
    }
    private final List<IClassyNodeListener> listeners;
    @Getter
    protected ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent)
    {
        this.name = name;
        listeners = new java.util.ArrayList<>();
        this.parent = parent;
    }

    protected void onChange()
    {
        for(var listener : listeners)
            listener.onNodeChanged(this);
    }

    public void addOnChangeListener(IClassyNodeListener listener) {
        listeners.add(listener);
    }
    public void removeOnChangeListener(IClassyNodeListener listener) {
        listeners.remove(listener);
    }

    @Override
    public String toString() {
        return name;
    }
}
