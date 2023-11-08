package raf.dsw.classycraft.app.tree;


import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite implements ClassyNode
{
    private List<ClassyNode> childNodes;
    public ClassyNodeComposite()
    {
        childNodes = new ArrayList<>();
    }
    public void addChild(ClassyNode child)
    {
        childNodes.add(child);
    }
    public void removeChild(ClassyNode child)
    {
        childNodes.remove(child);
    }
    public List<ClassyNode> getChildNodes()
    {
        return childNodes;
    }
}
