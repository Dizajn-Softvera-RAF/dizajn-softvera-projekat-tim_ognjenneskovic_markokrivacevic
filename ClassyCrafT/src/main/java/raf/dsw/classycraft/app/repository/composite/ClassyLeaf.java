package raf.dsw.classycraft.app.repository.composite;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public abstract class ClassyLeaf extends ClassyNode
{
    public ClassyLeaf(String name, ClassyNode parent)
    {
        super(name, parent);
    }

    @Override
    protected void onChange() {
        super.onChange();
        if(parent != null)
            ((ClassyNodeComposite)parent).onChildChange();
    }
}
