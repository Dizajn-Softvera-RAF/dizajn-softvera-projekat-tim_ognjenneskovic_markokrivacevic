package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AuthorNameAction extends AbstractClassyAction {

    public AuthorNameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit Author Name");
        putValue(SHORT_DESCRIPTION, "Edit Author Name");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String newAuthorName  = JOptionPane.showInputDialog("Enter new author name:");
        if(newAuthorName == null)
            return;
        if (newAuthorName.equals(""))
        {
            ApplicationFramework.getInstance().getMessageGenerator().
                    generateSystemMessage(SystemMessageType.NAME_CANNOT_BE_EMPTY, MessageType.ERROR);
            return;
        }

        var selectedProject = MainFrame.getInstance().getDesktopPanel().getSelectedProject();
        if (selectedProject != null)
            selectedProject.setAuthor(newAuthorName);
    }
}
