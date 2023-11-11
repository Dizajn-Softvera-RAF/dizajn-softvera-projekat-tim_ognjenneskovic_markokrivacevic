package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.RightPanel;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.IClassyNodeListener;
import raf.dsw.classycraft.app.repository.implementation.Project;

public class ProjectModifiedListener implements IClassyNodeListener
{
    public ProjectModifiedListener()
    {

    }
    @Override
    public void onNodeChanged(ClassyNode node) {
        var project = (Project) node;
        var rightPanel = MainFrame.getInstance().getRightPanel();
        rightPanel.setProjectLabel(project.getName());
        rightPanel.setAuthorLabel(project.getAuthor());
    }
}
