package raf.dsw.classycraft.app.tree;

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
