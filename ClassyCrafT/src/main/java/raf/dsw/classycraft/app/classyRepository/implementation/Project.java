package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    @Getter
    private String author;
    @Getter
    private String path;

    public void setAuthor(String author) {
        this.author = author;
        notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_CHANGED, this));
    }
    public void setPath(String path) {
        this.path = path;
        notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_CHANGED, this));
    }
    public Project(String name, ClassyNode parent) {
        super("Project", parent);
        author = "Author";
        path = "Path";
    }
}
