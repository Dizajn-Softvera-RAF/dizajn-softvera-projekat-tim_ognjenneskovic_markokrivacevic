package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child!=null && child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);

                notifySubscriber(new NodeChangeEvent(NodeEventType.CHILD_ADDED, child));
            }
        }
        notifySubscriber(this);
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if (child != null && child instanceof Project){
            this.getChildren().remove(child);

            child.notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_REMOVED, child));
            notifySubscriber(new NodeChangeEvent(NodeEventType.CHILD_REMOVED, child));
        }
    }
}
