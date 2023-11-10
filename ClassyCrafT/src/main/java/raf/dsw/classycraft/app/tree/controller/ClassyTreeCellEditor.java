package raf.dsw.classycraft.app.tree.controller;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private DefaultMutableTreeNode clickedObject = null;
    private JTextField textField = null;
    public ClassyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedObject = (DefaultMutableTreeNode)value;
        textField = new JTextField(value.toString());
        textField.addActionListener(this);
        return textField;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {
            return ((MouseEvent) event).getClickCount() == 3;
        }
        return false;
    }

    /**
     * Called when editing is finished
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedObject.getUserObject() instanceof ClassyNode))
            return;

        var node = (ClassyNode)clickedObject.getUserObject();
        node.setName(e.getActionCommand());
    }
}
