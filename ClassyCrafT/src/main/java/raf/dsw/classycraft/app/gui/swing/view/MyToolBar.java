package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.gui.swing.controller.TestMsgAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        var actionManager = MainFrame.getInstance().getActionManager();

        add(actionManager.getExitAction());

        // TODO: Remove this
        var msgAction = new TestMsgAction();
        add(msgAction);
    }
}
