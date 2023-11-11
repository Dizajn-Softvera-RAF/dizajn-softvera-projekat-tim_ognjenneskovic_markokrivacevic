package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel implements TreeSelectionListener, Subscriber {

    private Project project;
    private PackageView packageView;

    private JLabel pNameLabel;
    private JLabel aNameLabel;

    public ProjectView(){
        project = null;

        this.setLayout(new BorderLayout());

        this.pNameLabel = new JLabel("Project");
        this.aNameLabel = new JLabel("Author");

        add(pNameLabel);
        add(aNameLabel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        aNameLabel.setVisible(true);
        pNameLabel.setVisible(true);

        aNameLabel.setMaximumSize(new Dimension(150, 20));
        pNameLabel.setMaximumSize(new Dimension(150, 20));

        // Set size and add border
        setPreferredSize(new Dimension(200, 50));
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void refreshProjectView(Project selectedProject){
        this.project = selectedProject;
    }

    private void setProject(Project project){
        pNameLabel.setText(project.getName());
        aNameLabel.setText(project.getAuthor());
    }

    public void restart(){
        pNameLabel.setText("");
        aNameLabel.setText("");
    }

    public void deleteView(){
        project = null;
        packageView = null;
        pNameLabel.setText("");
        aNameLabel.setText("");
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        var node = treeItemSelected.getClassyNode();
        if(node instanceof Project)
        {
            if(project != null)
                project.removeSubscriber(this);

            var newProject = (Project) node;
            setProject(newProject);

            project = newProject;
            project.addSubscriber(this);
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Project) {
            var newProject = (Project) notification;
            setProject(newProject);
        }
    }
}
