package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.repository.implementation.ClassyTree;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.repository.implementation.Package;

import java.awt.event.ActionEvent;

public class NewDiagramAction extends AbstractClassyAction
{
    public NewDiagramAction()
    {
        putValue(NAME, "New Diagram");
        putValue(SHORT_DESCRIPTION, "New Diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var tree = ClassyTree.getInstance();
        if (!(tree.getSelectedNode() instanceof Package))
            throw new RuntimeException("A diagram can only be added to a package");


        var node = ClassyRepository.getInstance().createNode(NodeType.DIAGRAM, "New Diagram",
                (ClassyNodeComposite) tree.getSelectedNode());
        try {
            tree.addChild(tree.getSelectedNode(), node);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
