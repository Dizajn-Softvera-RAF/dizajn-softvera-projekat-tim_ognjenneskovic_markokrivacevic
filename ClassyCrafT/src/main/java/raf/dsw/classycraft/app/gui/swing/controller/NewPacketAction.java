package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.repository.implementation.ClassyTree;
import raf.dsw.classycraft.app.repository.composite.NodeType;

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
        var tree = ClassyTree.getInstance();
        if (!(tree.getSelectedNode() instanceof ClassyNodeComposite))
            throw new RuntimeException("Adding child nodes to this node is not allowed");
        var node = ClassyRepository.getInstance().createNode(NodeType.PACKAGE, "New Packet",
                (ClassyNodeComposite) tree.getSelectedNode());
        try {
            tree.addChild(tree.getSelectedNode(), node);
           // tree.generateTree(MainFrame.treePanel);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}