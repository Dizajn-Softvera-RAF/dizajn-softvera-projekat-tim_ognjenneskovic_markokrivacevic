package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.messageGenerator.MessageType;
import raf.dsw.classycraft.app.messageGenerator.SystemMessageType;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField edit = null;

    public ClassyTreeCellEditor(JTree arg0,DefaultTreeCellRenderer arg1){
        super(arg0,arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5){
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
   }

    @Override
    public boolean isCellEditable(EventObject arg0) {
        if(arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                 return true;
            }
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(!(clickedOn instanceof ClassyTreeItem))
           return;

       ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;

       var newName = e.getActionCommand();
       if(newName.equals("")) {
           ApplicationFramework.getInstance().getMessageGenerator().
                   generateSystemMessage(SystemMessageType.NAME_CANNOT_BE_EMPTY, MessageType.ERROR);
       }
       else
           clicked.setName(newName);
    }
}
