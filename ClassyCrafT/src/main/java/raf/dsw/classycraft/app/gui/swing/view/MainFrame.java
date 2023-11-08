package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.logging.Logger;
import raf.dsw.classycraft.app.logging.MessageType;
import raf.dsw.classycraft.app.tree.ClassyTree;
import raf.dsw.classycraft.app.tree.NodeType;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.sql.Timestamp;

public class MainFrame extends JFrame implements Logger {
    private static MainFrame instance;
    public static JPanel treeFrame;

    //buduca polja za sve komponente view-a na glavnom prozoru

    private MainFrame(){

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        setLayout(new BorderLayout());

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        treeFrame = new JPanel();
        add(treeFrame, BorderLayout.WEST);

        var tree = ClassyTree.getInstance();
        tree.generateTree(treeFrame);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void logMessage(String msg, MessageType type, Timestamp time) {
        String formattedMsg = String.format("[%s][%s] %s",type,time.toString(),msg);
        JOptionPane.showMessageDialog(null, formattedMsg);
    }
}
