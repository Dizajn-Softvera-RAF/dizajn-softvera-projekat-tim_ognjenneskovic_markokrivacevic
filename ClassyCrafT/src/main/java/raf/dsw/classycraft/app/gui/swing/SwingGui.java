package raf.dsw.classycraft.app.gui.swing;


import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;


public class SwingGui implements Gui {

    public SwingGui(){

    }

    @Override
    public void start() {
     MainFrame mainFrame = MainFrame.getInstance();
     mainFrame.setLocationRelativeTo(null);
     mainFrame.setVisible(true);
    }

}
