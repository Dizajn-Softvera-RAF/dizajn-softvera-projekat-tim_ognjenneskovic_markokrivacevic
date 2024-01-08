package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAction extends AbstractClassyAction{

    public SaveProjectAction(){
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        Project project =(Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        if(project==null)
            return;
        File projectFile;
        if (project.getFilePath()==null || project.getFilePath().trim().equals("")){
            if(jFileChooser.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
               projectFile = jFileChooser.getSelectedFile();
               project.setFilePath(projectFile.getPath());
            }
        }
        ApplicationFramework.getInstance().getSerializer().saveProject(project);
    }
}
