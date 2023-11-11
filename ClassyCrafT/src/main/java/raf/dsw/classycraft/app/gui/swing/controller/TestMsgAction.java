package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.messageGenerator.MessageType;

import java.awt.event.ActionEvent;
import java.sql.Timestamp;

public class TestMsgAction extends AbstractClassyAction
{
    public TestMsgAction()
    {
        var icon = loadIcon("/images/info.png");
        putValue(SMALL_ICON, icon);
        putValue(NAME, "Test MSG");
        putValue(SHORT_DESCRIPTION, "Test MSG");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var currentTime = new Timestamp(System.currentTimeMillis());
        ApplicationFramework.getInstance().testGenerator.generateMessage(
                new Message("Test msg", MessageType.INFO, currentTime));
    }
}
