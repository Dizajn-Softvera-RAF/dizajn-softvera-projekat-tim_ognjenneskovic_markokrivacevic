package raf.dsw.classycraft.app.gui.swing.factory;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;

public class PackageFactory extends NodeFactory{

    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        return new Package("Package",parent);
    }
}
