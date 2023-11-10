package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.repository.implementation.ClassyTree;
import raf.dsw.classycraft.app.repository.composite.NodeType;

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
        if (!(tree.getSelectedNode() instanceof ClassyNodeComposite))
            throw new RuntimeException("Adding child nodes to this node is not allowed");
        var node = ClassyRepository.getInstance().createNode(
                NodeType.PROJECT, "Test Project", "Test Author", "New Path",
                (ClassyNodeComposite) tree.getSelectedNode());
        try {
            tree.addChild(tree.getSelectedNode(), node);
            //tree.generateTree(MainFrame.treePanel);
        } catch (Exception ex) {
            // TODO: Throw proper exception
            throw new RuntimeException(ex);
        }
    }
}
