package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.NodeChangeEvent;
import raf.dsw.classycraft.app.classyRepository.NodeEventType;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

@Getter
@Setter
public class Package extends ClassyNodeComposite {


    private boolean template;

    public Package(String name, ClassyNode parent) {
        super("Package", parent);
        this.template = template;
    }

    public boolean isTemplate() {
        return template;
    }

    public void setTemplate(boolean template) {
        this.template = template;
    }
}
