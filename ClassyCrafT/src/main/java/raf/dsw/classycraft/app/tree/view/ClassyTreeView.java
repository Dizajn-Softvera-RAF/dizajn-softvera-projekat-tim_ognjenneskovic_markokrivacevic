package raf.dsw.classycraft.app.tree.view;

import raf.dsw.classycraft.app.tree.controller.ClassyTreeCellEditor;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree
{
    public ClassyTreeView(DefaultTreeModel defaultTreeModel)
    {
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
        setCellEditor(new ClassyTreeCellEditor(this, classyTreeCellRenderer));
        setCellRenderer(classyTreeCellRenderer);
        setEditable(true);
    }
}
