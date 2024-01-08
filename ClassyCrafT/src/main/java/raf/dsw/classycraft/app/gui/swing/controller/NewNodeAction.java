package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractClassyAction{

    public NewNodeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("/images/new.png"));
        putValue(NAME,"Add New");
        putValue(SHORT_DESCRIPTION,"Add New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected.getClassyNode() instanceof Diagram) {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.CREATE_CHILD_ON_DIAGRAM, MessageType.ERROR);
            return;
        }
        if(selected.getClassyNode() instanceof DiagramElement) {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.NODE_CANNOT_HAVE_CHILDREN, MessageType.ERROR);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
