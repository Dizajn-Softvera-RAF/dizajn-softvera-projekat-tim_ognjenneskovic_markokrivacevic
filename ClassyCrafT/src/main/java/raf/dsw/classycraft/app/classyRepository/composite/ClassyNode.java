package raf.dsw.classycraft.app.classyRepository.composite;

import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNode implements Publisher
{

    private String name;
    private ClassyNode parent;

    protected List<Subscriber> subscribers;

    public ClassyNode(String name,ClassyNode parent){
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_CHANGED, this));
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    @Override
    public void addSubscriber(Subscriber sub)
    {
        subscribers.add(sub);
    }
    @Override
    public void removeSubscriber(Subscriber sub)
    {
        subscribers.remove(sub);
    }
    @Override
    public void notifySubscriber(Object notification)
    {
        // Iterate over a copy of subscribers list to avoid possibly modifying the list while iterating
        var subscribersCopy = new ArrayList<>(subscribers);
        for(var sub: subscribersCopy)
            sub.update(notification);
//        if(parent != null)
//            parent.notifySubscriber(notification);
    }

}
