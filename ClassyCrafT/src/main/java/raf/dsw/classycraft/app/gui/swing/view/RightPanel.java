package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.repository.composite.NodeType;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.tree.controller.ProjectListener;

import javax.swing.*;
import java.awt.*;

public class RightPanel
{
    private JLabel projectLabel, authorLabel;
    public RightPanel(JFrame frame)
    {

        projectLabel = new JLabel("Project: ");
        authorLabel = new JLabel("Author: ");

        var listener = new ProjectListener(this);
        ClassyRepository.getInstance().addOnChangeListener(listener, NodeType.PROJECT);

        MainFrame.getInstance().getClassyTree().addOnSelectionListener(e-> {
            if (e.getClass() == Project.class)
                listener.onNodeChanged(e);
        });

        var panel = new JPanel();
        panel.add(projectLabel);
        panel.add(authorLabel);

        frame.add(panel, BorderLayout.EAST);
    }
    public void setProjectLabel(String text)
    {
        projectLabel.setText(text);
    }
    public void setAuthorLabel(String text)
    {
        authorLabel.setText(text);
    }
}
