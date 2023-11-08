package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.NodeType;

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
        var node = tree.createNode(NodeType.PACKAGE, "New Packet");
        try {
            tree.addChild(tree.getSelectedNode(), node);
            tree.generateTree(MainFrame.treeFrame);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}