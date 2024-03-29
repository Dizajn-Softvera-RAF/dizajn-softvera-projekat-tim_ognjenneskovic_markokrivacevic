package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance ;

    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolbar;
    private ClassyTree classyTree;
    private ClassyTreeView projectExplorer;
    private ProjectView desktopPanel;

    private JPanel desktop;

    private PackageView tabbedPanel;


    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImplementation();
        projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer());
        initialiseGui(projectExplorer);
    }

    private void initialiseGui(ClassyTreeView projcetExplorer){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth *3/4, screenHeight * 3/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        menu = new MyMenyBar();
        setJMenuBar(menu);

        toolbar = new MyToolBar();
        add(toolbar,BorderLayout.NORTH);

        desktopPanel = new ProjectView();
        desktop = new JPanel();
        desktop.setLayout(new BorderLayout());
        desktop.add(desktopPanel, BorderLayout.NORTH);

        tabbedPanel = new PackageView();
        desktop.add(tabbedPanel, BorderLayout.CENTER);

        projcetExplorer.addTreeSelectionListener(tabbedPanel);
        projcetExplorer.addTreeSelectionListener(desktopPanel);

        JScrollPane scroll = new JScrollPane(projcetExplorer);
        scroll.setMinimumSize(new Dimension(200,150));

        var sideBar = new SideBar();
        var rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, desktop, sideBar);
        rightSplit.setDividerLocation(600);
        rightSplit.setOneTouchExpandable(true);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, rightSplit);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }


    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager(){
        return actionManager;
    }

    @Override
    public void update(Object obj) {
        Message msg = (Message) obj;
        String formattedMsg = String.format("[%s][%s] %s",
                msg.getTip(),
                msg.getVreme().toString(),msg.getPoruka());
        JOptionPane.showMessageDialog(null, formattedMsg);
    }
}
