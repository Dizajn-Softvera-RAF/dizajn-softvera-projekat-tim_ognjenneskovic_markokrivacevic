package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.net.URL;

public class SideBar extends JToolBar
{
    public SideBar()
    {
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getAddClassAction());
        add(MainFrame.getInstance().getActionManager().getAddInheritanceAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());

        JButton zoomButton = new JButton();
        URL imageURL = getClass().getResource("/images/zoom.png");
        ImageIcon icon = new ImageIcon(imageURL);
        zoomButton.setIcon(icon);
        JPopupMenu submenu = new JPopupMenu();
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomInAction()));
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomOutAction()));

        zoomButton.addActionListener(e -> {
            // Show the submenu to the left of the button
            submenu.show(zoomButton, -submenu.getPreferredSize().width, 0);
        });
        add(zoomButton);

        add(MainFrame.getInstance().getActionManager().getMoveAction());

    }
}
