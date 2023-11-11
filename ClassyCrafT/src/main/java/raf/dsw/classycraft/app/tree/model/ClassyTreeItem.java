package raf.dsw.classycraft.app.tree.model;

import lombok.Getter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode
{
    @Getter
    private final ClassyNode node;
    public ClassyTreeItem(ClassyNode node)
    {
        this.node = node;
    }
    @Override
    public String toString() {
        return node.getName();
    }
    public void setName(String name) {
        node.setName(name);
    }
}
