package raf.dsw.classycraft.app.classyRepository.composite;

public abstract class ClassyNode {

    private String name;
    private ClassyNode parent;

    public ClassyNode(String name,ClassyNode parent){
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }
}
