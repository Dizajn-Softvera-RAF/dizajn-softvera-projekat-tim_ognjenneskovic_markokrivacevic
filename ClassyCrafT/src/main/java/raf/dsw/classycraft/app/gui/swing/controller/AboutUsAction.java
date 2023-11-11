package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction
{
    public AboutUsAction()
    {
        var icon = loadIcon("/images/info.png");
        putValue(SMALL_ICON, icon);
        putValue(NAME, "About Us");
        putValue(SHORT_DESCRIPTION, "About Us");
    }
    private JPanel setAuthorContent(String first_name, String last_name, String picture)
    {
        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(10,100,10,100));

        // Add first name label, change font size and center
        var fname = new JLabel(first_name);
        fname.setFont(new Font("Serif", Font.PLAIN, 16));
        fname.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add last name label, change font size and center
        var lname = new JLabel(last_name);
        lname.setFont(new Font("Serif", Font.PLAIN, 16));
        lname.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add picture
        var pictureIcon = loadIcon(picture, 100, 100);
        var pictureLabel = new JLabel(pictureIcon);
        pictureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(fname);
        panel.add(lname);
        panel.add(pictureLabel);
        return panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("About Us");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

        var leftCol = setAuthorContent("Ognjen", "Neskovic","/images/ognjen_neskovic.jpg");
        var rightCol = setAuthorContent("Marko", "Krivacevic","...");

        contentPanel.add(leftCol);
        contentPanel.add(rightCol);

        frame.setContentPane(contentPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
