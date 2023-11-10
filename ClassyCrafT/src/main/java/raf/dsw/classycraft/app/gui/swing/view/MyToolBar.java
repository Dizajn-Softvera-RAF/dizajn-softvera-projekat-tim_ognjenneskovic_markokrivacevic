package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ExitAction;
import raf.dsw.classycraft.app.controller.TestMsgAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
