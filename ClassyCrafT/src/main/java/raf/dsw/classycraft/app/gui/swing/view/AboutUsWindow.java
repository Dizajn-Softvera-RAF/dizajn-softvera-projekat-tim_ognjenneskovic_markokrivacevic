package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.JScrollPane;

public class AboutUsWindow extends JFrame {

    public AboutUsWindow(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3,screenHeight/3);
        setLocationRelativeTo(null);
        setTitle("AboutUs");

        JLabel s1 = new JLabel("Marko Krivacevic 119/21 RN");
        JLabel s2 = new JLabel("Ognjen Neskovic 6/22 RN");
        var picture1 = makeImage("/images/marko_krivacevic.jpg");
        var picture2 = makeImage("/images/ognjen_neskovic.jpg");


        JPanel prvi = new JPanel();
        prvi.setLayout(new BoxLayout(prvi,BoxLayout.Y_AXIS));
        JPanel drugi = new JPanel();
        drugi.setLayout(new BoxLayout(drugi,BoxLayout.Y_AXIS));

        prvi.setMinimumSize(new Dimension(300,300));
        drugi.setMinimumSize(new Dimension(300,300));

        s1.setAlignmentX(Component.CENTER_ALIGNMENT);
        s2.setAlignmentX(Component.CENTER_ALIGNMENT);
        prvi.add(s1);
        prvi.add(picture1);
        drugi.add(s2);
        drugi.add(picture2);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,prvi,drugi);
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
            icon = new ImageIcon(newimg);
            var label = new JLabel(icon);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            return label;
        }
        return null;

    }

}
