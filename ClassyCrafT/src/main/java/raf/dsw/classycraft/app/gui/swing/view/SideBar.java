package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class SideBar extends JToolBar
{
    public SideBar()
    {
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getAddClassAction());
        add(MainFrame.getInstance().getActionManager().getAddInheritanceAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
    }
}
