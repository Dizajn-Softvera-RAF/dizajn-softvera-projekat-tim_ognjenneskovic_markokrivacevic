package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.logging.ILogger;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.repository.implementation.ClassyTree;
import raf.dsw.classycraft.app.tree.controller.OpenPackageListener;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

@Getter
@Setter
public class MainFrame extends JFrame implements ILogger {
    private static MainFrame instance;
    public static JPanel treePanel;
    public static DiagramTabs diagramTabs;
    public static RightPanel rPanel;

    private ActionManager actionManager;
    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();
        initializeGUI();
    }
    private void initializeGUI(){
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

        treePanel = new JPanel();
        add(treePanel, BorderLayout.WEST);
        var tree = ClassyTree.getInstance();
        tree.generateTree(treePanel);

        rPanel = new RightPanel(this);
        diagramTabs = new DiagramTabs(this);
        ClassyTree.getInstance().addOnSelectionListener(new OpenPackageListener(diagramTabs));
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
    public void logMessage(Message msg) {
        String formattedMsg = String.format("[%s][%s] %s",msgTypeStrMap.get(msg.getType()),
                msg.getTime().toString(),msg.getMsg());
        JOptionPane.showMessageDialog(null, formattedMsg);
    }
}
