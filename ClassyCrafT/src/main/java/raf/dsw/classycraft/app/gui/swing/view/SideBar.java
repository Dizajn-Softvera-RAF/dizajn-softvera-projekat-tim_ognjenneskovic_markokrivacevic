package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JButton inheritanceButton = getIconButton("/images/arrow.png");
        JPopupMenu inherMenu = new JPopupMenu();
        inherMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddAssociationAction()));
        inherMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddGeneralisationAction()));
        inherMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddAggregationAction()));
        inherMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddCompositionAction()));
        inheritanceButton.addActionListener(e->{
            inherMenu.show(inheritanceButton,-inherMenu.getPreferredSize().width,0);
        });
        add(inheritanceButton);

        JButton zoomButton = getIconButton("/images/zoom.png");

        JPopupMenu submenu = new JPopupMenu();
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomInAction()));
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomOutAction()));

        zoomButton.addActionListener(e -> {
            // Show the submenu to the left of the button
            submenu.show(zoomButton, -submenu.getPreferredSize().width, 0);
        });
        add(zoomButton);

        add(MainFrame.getInstance().getActionManager().getPanAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());

        JButton openPopupButton = getIconButton("/images/edit_note.png");
        openPopupButton.addActionListener(e -> {
            var selectedPainters = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().getSelectedPainters();
            if (selectedPainters.size() != 1)
                return;
            var popup = new EditClassPopup(MainFrame.getInstance(), (Interclass) selectedPainters.get(0).getElement());
            popup.setVisible(true);
        });
        add(openPopupButton);
    }

    private JButton getIconButton(String path)
    {
        JButton button = new JButton();
        URL imageURL = getClass().getResource(path);
        ImageIcon icon = new ImageIcon(imageURL);
        button.setIcon(icon);
        return button;
    }
}
