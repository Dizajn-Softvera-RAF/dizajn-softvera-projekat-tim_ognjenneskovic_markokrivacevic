package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);
        add (MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        add(MainFrame.getInstance().getActionManager().getDeleteChildAction());
        add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        add(MainFrame.getInstance().getActionManager().getAuthorNameAction());
        add(MainFrame.getInstance().getActionManager().getPictureAction());
    }
}
