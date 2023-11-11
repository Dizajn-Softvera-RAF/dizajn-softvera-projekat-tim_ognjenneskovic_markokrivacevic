package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.tree.ClassyTree;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class ClassyTreeCellEditorListener implements CellEditorListener {
    private JTextField textField;
    public ClassyTreeCellEditorListener(JTextField textField) {
        this.textField = textField;
    }
    @Override
    public void editingStopped(ChangeEvent e) {
        var node = MainFrame.getInstance().getClassyTree().getSelectedNode();
        node.setName(textField.getText());
    }

    @Override
    public void editingCanceled(ChangeEvent e) {

    }
}
