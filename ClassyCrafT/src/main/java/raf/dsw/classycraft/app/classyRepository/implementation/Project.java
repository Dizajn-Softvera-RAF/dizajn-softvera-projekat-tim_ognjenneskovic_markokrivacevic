package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    @Getter
    protected String filePath;
    @Getter
    private String author;

    public void setAuthor(String author) {
        this.author = author;
        notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_CHANGED, this));
    }
    public void setFilePath(String path) {
        this.filePath = path;
        notifySubscriber(new NodeChangeEvent(NodeEventType.NODE_CHANGED, this));
    }
    public Project(String name, ClassyNode parent) {
        super("Project", parent);
        author = "Author";
        filePath = null;
    }
    public Project(String name, ClassyNode parent, String author, String filePath) {
        super(name, parent);
        this.author = author;
        this.filePath = filePath;
    }
}
