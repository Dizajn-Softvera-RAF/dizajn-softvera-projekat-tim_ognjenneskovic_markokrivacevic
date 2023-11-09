package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.NodeType;

import java.awt.event.ActionEvent;

public class NewProjectAction extends AbstractClassyAction
{
    public NewProjectAction()
    {
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var tree = ClassyTree.getInstance();
        var node = tree.createNode(NodeType.PROJECT, "New Project", "Author", "Path");
        try {
            tree.addChild(tree.getSelectedNode(), node);
            tree.generateTree(MainFrame.treePanel);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
