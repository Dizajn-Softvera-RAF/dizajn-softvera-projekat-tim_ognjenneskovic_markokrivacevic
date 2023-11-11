package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import java.awt.*;

public class DiagramTabs
{
    private JTabbedPane tabbedPane;
    private JFrame frame;
    public DiagramTabs(JFrame frame)
    {
        this.frame=frame;
        tabbedPane =new JTabbedPane();
        frame.add(tabbedPane, BorderLayout.CENTER);
    }
    public void OpenTabs (Package pckg)
    {
        frame.remove(tabbedPane);

        tabbedPane =new JTabbedPane();
        tabbedPane.setBounds(50,50,200,200);
        for (var child : pckg.getChildren())
        {
            if (child instanceof Diagram) {
                var diagram = (Diagram) child;
                tabbedPane.add(diagram.getName(), new JPanel());
            }
        }
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}
