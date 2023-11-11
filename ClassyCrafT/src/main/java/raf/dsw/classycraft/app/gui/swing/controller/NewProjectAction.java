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
        putValue(NAME, "Add new");
        putValue(SHORT_DESCRIPTION, "Add new");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var tree = MainFrame.getInstance().getClassyTree();
        var selectedTreeItem = tree.getSelectedNode();
        var parent = selectedTreeItem.getNode();

        if(!(parent instanceof ClassyNodeComposite))
            return; // TODO: Throw exception

        var node = ClassyRepository.getInstance().createNode((ClassyNodeComposite) parent);
        try {
            tree.addChild(selectedTreeItem, node);
        }
        catch (Exception ex) {
            // TODO: Handle exception
        }
    }
}
