package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.RightPanel;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;

public class ProjectListener implements IClassyNodeListener
{
    private RightPanel rightPanel;
    public ProjectListener(RightPanel rightPanel)
    {
        this.rightPanel = rightPanel;
    }
    @Override
    public void onNodeChanged(ClassyNode node) {
        var project = (Project) node;
        rightPanel.setProjectLabel(project.getName());
        rightPanel.setAuthorLabel(project.getAuthor());
    }
}
