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
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        tabbedPane =new JTabbedPane();
        tabbedPane.setBounds(50,50,200,200);
        tabbedPane.add("Panel 1",p1);
        tabbedPane.add("Panel 2",p2);
        tabbedPane.add("Panel 3",p3);
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
