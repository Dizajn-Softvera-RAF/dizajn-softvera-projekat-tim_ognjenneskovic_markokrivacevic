package raf.dsw.classycraft.app.gui.swing.factory;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;

public abstract class NodeFactory {

    public ClassyNode getNode(ClassyNodeComposite parent){
        ClassyNode n = createNode(parent);
        n.setName(n.getName() + " " + (parent.getChildren().size() + 1));
        parent.addChild(n);
        return n;
    }

    public abstract ClassyNode createNode(ClassyNodeComposite parent);
}
