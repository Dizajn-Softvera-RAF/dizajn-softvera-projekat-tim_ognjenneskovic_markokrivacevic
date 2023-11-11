package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    private String autor;

    public Project(String name, ClassyNode parent) {
        super("Project", parent);
        this.autor = autor;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child!=null && child instanceof Package){
            Package aPackage = (Package) child;
            if(!this.getChildren().contains(aPackage)){
                this.getChildren().add(aPackage);
            }
        }
    }

    @Override
    public void deleteChild(ClassyNode child) {
         if(child!=null && child instanceof Package){
             this.getChildren().remove(child);
         }
    }
}
