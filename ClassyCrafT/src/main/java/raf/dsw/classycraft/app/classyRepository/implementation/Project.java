package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    @Getter
    private String author;
    @Getter
    private String path;

    public void setAuthor(String author) {
        this.author = author;
        notifySubscriber(this);
    }
    public void setPath(String path) {
        this.path = path;
        notifySubscriber(this);
    }
    public Project(String name, ClassyNode parent) {
        super("Project", parent);
        // TODO: Add input for atuhro name and path
        author = "Author";
        path = "Path";
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package){
            Package aPackage = (Package) child;
            if(!this.getChildren().contains(aPackage)){
                this.getChildren().add(aPackage);
            }
        }
        notifySubscriber(this);
    }

    @Override
    public void deleteChild(ClassyNode child) {
         if(child instanceof Package){
             this.getChildren().remove(child);
         }
        notifySubscriber(this);
    }
}
