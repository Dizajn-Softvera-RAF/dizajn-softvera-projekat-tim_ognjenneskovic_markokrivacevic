package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class RightPanel
{
    private JLabel projectLabel, authorLabel;
    public RightPanel(JFrame frame)
    {

        projectLabel = new JLabel("Project: ");
        authorLabel = new JLabel("Author: ");

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
