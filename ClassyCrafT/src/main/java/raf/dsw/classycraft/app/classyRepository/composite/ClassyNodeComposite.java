package raf.dsw.classycraft.app.classyRepository.composite;

import java.util.List;
import java.util.ArrayList;

public abstract class ClassyNodeComposite extends ClassyNode
{

    List<ClassyNode> children;

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassyNode> children) {
        this.children = children;
    }

    public abstract void addChild(ClassyNode child);

    public abstract void deleteChild(ClassyNode child);

}
