package raf.dsw.classycraft.app.classyRepository.composite;

import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;

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

    public void addChild(ClassyNode child)
    {
        if(child!=null && !children.contains(child)){
            children.add(child);
            notifySubscriber(new NodeChangeEvent(NodeEventType.CHILD_ADDED, child));
        }
    }

    public void deleteChild(ClassyNode child)
    {
        if(child!=null && children.contains(child))
        {
            children.remove(child);

            child.notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_REMOVED, child));
            notifySubscriber(new NodeChangeEvent(NodeEventType.CHILD_REMOVED, child));
        }
    }

}
