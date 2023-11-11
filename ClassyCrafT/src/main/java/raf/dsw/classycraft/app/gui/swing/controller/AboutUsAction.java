package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.AboutUsWindow;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction(){
        putValue(SMALL_ICON,loadIcon("/images/info.png"));
        putValue(NAME,"AboutUs");
        putValue(SHORT_DESCRIPTION,"AboutUs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsWindow window = new AboutUsWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
