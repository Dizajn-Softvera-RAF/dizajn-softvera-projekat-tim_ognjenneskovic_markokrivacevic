package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;

import javax.swing.*;
import java.net.URL;

public class SideBar extends JToolBar
{
    public SideBar()
    {
        super(VERTICAL);
        setFloatable(false);
        JButton addInterclassButton = getIconButton("/images/new.png");
        JPopupMenu addMenu = new JPopupMenu();
        addMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddClassAction()));
//        addMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddInterfaceAction()));
//        addMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddEnumAction()));
        addInterclassButton.addActionListener(e->{
            addMenu.show(addInterclassButton,-addMenu.getPreferredSize().width,0);
        });
        add(addInterclassButton);
        add(MainFrame.getInstance().getActionManager().getSelectAction());

        JButton addConnectionButton = getIconButton("/images/arrow.png");
        JPopupMenu connMenu = new JPopupMenu();
        connMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddDependencyAction()));
        connMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddGeneralisationAction()));
        connMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddAggregationAction()));
        connMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAddCompositionAction()));
        addConnectionButton.addActionListener(e->{
            connMenu.show(addConnectionButton,-connMenu.getPreferredSize().width,0);
        });
        add(addConnectionButton);

        JButton zoomButton = getIconButton("/images/zoom.png");

        JPopupMenu submenu = new JPopupMenu();
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomInAction()));
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomOutAction()));
        submenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getZoomToFitAction()));

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
