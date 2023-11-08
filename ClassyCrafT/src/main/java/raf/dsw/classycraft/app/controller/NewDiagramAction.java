package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.NodeType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
        var node = tree.createNode(NodeType.DIAGRAM, "New Diagram");
        try {
            tree.addChild(tree.getSelectedNode(), node);
            tree.generateTree(MainFrame.treeFrame);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
