package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteChildAction extends AbstractClassyAction{

    public DeleteChildAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.NODE_CANNOT_BE_DELETED, MessageType.ERROR);
            return;
        }
        if(selected.getClassyNode() instanceof DiagramElement)
        {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.DELETE_CHILD_ON_DIAGRAM_ELEMENT, MessageType.ERROR);
            return;
        }
        MainFrame.getInstance().getClassyTree().removeChild(selected);
        MainFrame.getFrames().clone();

    }
}
