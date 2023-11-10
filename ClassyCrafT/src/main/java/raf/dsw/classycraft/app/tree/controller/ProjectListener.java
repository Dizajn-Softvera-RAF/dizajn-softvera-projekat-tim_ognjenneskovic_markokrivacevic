package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;

public class ProjectListener implements IClassyNodeListener
{
    private JLabel projectNameLabel, projectAuthorLabel;
    public ProjectListener(JLabel projectNameLabel, JLabel projectAuthorLabel)
    {
        this.projectNameLabel = projectNameLabel;
        this.projectAuthorLabel = projectAuthorLabel;
    }
    @Override
    public void onNodeChanged(ClassyNode node) {
        var project = (Project) node;
        projectNameLabel.setText(project.getName());
        projectAuthorLabel.setText(project.getAuthor());
    }
}
