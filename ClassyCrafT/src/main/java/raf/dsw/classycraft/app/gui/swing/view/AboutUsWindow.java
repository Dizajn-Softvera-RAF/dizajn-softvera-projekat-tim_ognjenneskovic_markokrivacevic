package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
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



        JPanel prvi = new JPanel();
        JPanel drugi = new JPanel();

        prvi.setMinimumSize(new Dimension(300,300));
        drugi.setMinimumSize(new Dimension(300,300));

        prvi.add(s1);
        drugi.add(s2);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,prvi,drugi);
        split.setDividerLocation(200);
        getContentPane().add(split,BorderLayout.CENTER);

    }


}
