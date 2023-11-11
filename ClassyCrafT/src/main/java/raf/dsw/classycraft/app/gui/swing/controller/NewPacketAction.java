package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import java.awt.event.ActionEvent;

public class NewPacketAction extends AbstractClassyAction
{
    public NewPacketAction()
    {
        putValue(NAME, "New Packet");
        putValue(SHORT_DESCRIPTION, "New Packet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var tree = MainFrame.getInstance().getClassyTree();
        if (    !(tree.getSelectedNode().getNode() instanceof Project) &&
                !(tree.getSelectedNode().getNode() instanceof Package))
            throw new RuntimeException("A packet can only be added to a project or a package");

        var node = ClassyRepository.getInstance().createNode(NodeType.PACKAGE, "New Packet",
                (ClassyNodeComposite) tree.getSelectedNode().getNode());
        try {
            tree.addChild(tree.getSelectedNode(), node);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}