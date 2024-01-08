package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenProjectAction extends AbstractClassyAction{

    public OpenProjectAction(){
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open project");
        putValue(SMALL_ICON,loadIcon("/images/openn.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();

        if(jFileChooser.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
           var jsonFile = jFileChooser.getSelectedFile();
           try {
               ApplicationFramework.getInstance().getSerializer().loadProject(jsonFile);

               MainFrame.getInstance().getClassyTree().repaintTree();
           }
           catch (Exception exception){
               ApplicationFramework.getInstance().getMessageGenerator().generateSystemMessage(
                       SystemMessageType.INVALID_JSON, MessageType.ERROR);
           }
        }
    }
}
