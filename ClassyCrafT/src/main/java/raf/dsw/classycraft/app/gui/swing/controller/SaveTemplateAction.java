package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractClassyAction {

    public SaveTemplateAction(){
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save template");
        putValue(SMALL_ICON,loadIcon("/images/template.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();

        if (diagramView==null) {
            return;
        }
        String templateName = JOptionPane.showInputDialog(new JFrame(),"Unesite ime sablona","Template name",JOptionPane.PLAIN_MESSAGE);
        if(templateName==null || templateName.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().generateSystemMessage(SystemMessageType.NAME_CANNOT_BE_EMPTY, MessageType.ERROR);
            return;
        }
        ApplicationFramework.getInstance().getSerializer().saveTemplate((Diagram) diagramView.getSelectedDiagram().getClassyNode(),templateName);
    }
}
