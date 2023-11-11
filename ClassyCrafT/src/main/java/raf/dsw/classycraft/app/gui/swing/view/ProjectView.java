package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;

public class ProjectView extends JPanel implements TreeSelectionListener, Subscriber {

    @Getter
    private Project selectedProject;
    private PackageView packageView;

    private JLabel pNameLabel;
    private JLabel aNameLabel;

    public ProjectView(){
        selectedProject = null;

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
        this.selectedProject = selectedProject;
    }

    private void setSelectedProject(Project selectedProject){
        pNameLabel.setText(selectedProject.getName());
        aNameLabel.setText(selectedProject.getAuthor());
    }

    public void restart(){
        pNameLabel.setText("");
        aNameLabel.setText("");
    }

    public void deleteView(){
        selectedProject = null;
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
            if(selectedProject != null)
                selectedProject.removeSubscriber(this);

            var newProject = (Project) node;
            setSelectedProject(newProject);

            selectedProject = newProject;
            selectedProject.addSubscriber(this);
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Project) {
            var newProject = (Project) notification;
            setSelectedProject(newProject);
        }
    }
}
