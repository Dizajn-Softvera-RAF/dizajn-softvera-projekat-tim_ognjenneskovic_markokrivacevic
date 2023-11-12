package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AboutUsWindow extends JFrame {

    public AboutUsWindow(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3,screenHeight/3);
        setLocationRelativeTo(null);
        setTitle("AboutUs");

        JLabel label1 = new JLabel("Marko Krivacevic 119/21 RN");
        JLabel label2 = new JLabel("Ognjen Neskovic 6/22 RN");
        var picture1 = makeImage("/images/marko_krivacevic.jpg");
        var picture2 = makeImage("/images/ognjen_neskovic.jpg");


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));

        leftPanel.setMinimumSize(new Dimension(300,300));
        rightPanel.setMinimumSize(new Dimension(300,300));

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(label1);
        leftPanel.add(picture1);
        rightPanel.add(label2);
        rightPanel.add(picture2);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel, rightPanel);
        getContentPane().add(split,BorderLayout.CENTER);

    }

    private JLabel makeImage(String imgPath)
    {
        URL imageURL = getClass().getResource(imgPath);
        if(imageURL != null)
        {
            var icon = new ImageIcon(imageURL);
            // Resize image
            Image image = icon.getImage();
            Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
            // Set new image
            icon = new ImageIcon(newimg);
            var label = new JLabel(icon);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            return label;
        }
        return null;

    }

}
