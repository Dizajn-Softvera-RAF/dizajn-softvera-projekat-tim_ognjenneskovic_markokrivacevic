package raf.dsw.classycraft.app.gui.swing.controller;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

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
        var tree = MainFrame.getInstance().getClassyTree();
        if (!(tree.getSelectedNode().getNode() instanceof ProjectExplorer))
            throw new RuntimeException("A project can only be added to a project explorer");

        var node = ClassyRepository.getInstance().createNode(
                NodeType.PROJECT, "Test Project", "Test Author", "New Path",
                (ClassyNodeComposite) tree.getSelectedNode().getNode());
        try {
            tree.addChild(tree.getSelectedNode(), node);
        } catch (Exception ex) {
            // TODO: Throw proper exception
            throw new RuntimeException(ex);
        }
    }
}
