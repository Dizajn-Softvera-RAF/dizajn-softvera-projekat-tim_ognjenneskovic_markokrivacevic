package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel implements Subscriber {

    private Project project;
    private PackageView packageView;

    private JLabel pNameLabel;
    private JLabel aNameLabel;

    private JTabbedPane tabbedPane;
    private List<PackageView> tabs;

    public ProjectView(){
        this.setLayout(new BorderLayout());


        this.pNameLabel = new JLabel("Project");
        this.aNameLabel = new JLabel("Author");

        this.tabbedPane = new JTabbedPane();
        this.tabs = new ArrayList<>();

        add(pNameLabel);
        add(aNameLabel);
        add(tabbedPane);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        aNameLabel.setVisible(true);
        pNameLabel.setVisible(true);

        aNameLabel.setMaximumSize(new Dimension(150, 20));
        pNameLabel.setMaximumSize(new Dimension(150, 20));


    }

    public void refreshProjectView(Project selectedProject){

        tabs.clear();
        tabbedPane.removeAll();

        if(project != null) {

        }
        this.project = selectedProject;

        for(ClassyNode child : ((Project) selectedProject).getChildren()){
            PackageView tab = new PackageView((Package) child);
            tabs.add(tab);
        }
        for(PackageView tab:tabs){
           tabbedPane.addTab(tab.getPackage().getName(),tab);
        }

    }

    public void setProject(Project project){
        this.project = project;
        pNameLabel.setText(project.getName());
        this.update(project);

    }

    public void restart(){
        pNameLabel.setText("");
        tabbedPane.removeAll();
    }

    public void deleteView(){
        tabbedPane.removeAll();
        project = null;
        packageView = null;
        pNameLabel.setText("");

    }

    @Override
    public void update(Object notification) {
        if(project==null){
            return;
        }
    }
}
