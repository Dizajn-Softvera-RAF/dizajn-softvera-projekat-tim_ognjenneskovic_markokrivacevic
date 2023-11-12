package raf.dsw.classycraft.app.classyRepository;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;

@Getter
public class NodeChangeEvent
{
    private NodeEventType type;
    private ClassyNode node;
    public NodeChangeEvent(NodeEventType type, ClassyNode node)
    {
        this.type = type;
        this.node = node;
    }
}
