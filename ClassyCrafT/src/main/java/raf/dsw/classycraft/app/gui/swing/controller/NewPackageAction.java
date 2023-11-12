package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewPackageAction extends AbstractClassyAction{

    public NewPackageAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("/images/new_package.png"));
        putValue(NAME,"New Package");
        putValue(SHORT_DESCRIPTION,"New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();

        if(selected.getClassyNode() instanceof Package)
        {
            isCurrentAction = true;
            MainFrame.getInstance().getClassyTree().addChild(selected);
            isCurrentAction = false;
        }
        else {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.NODE_CANNOT_HAVE_PACKAGE, MessageType.ERROR);
        }
    }
}
