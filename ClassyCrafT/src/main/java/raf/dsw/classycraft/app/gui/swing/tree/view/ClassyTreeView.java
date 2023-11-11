package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeSelectionListener;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClassyTreeView extends JTree implements MouseListener {


    public ClassyTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer ruTreeCellRenderer = new ClassyTreeCellRenderer();

        addTreeSelectionListener(new ClassyTreeSelectionListener());

        setCellEditor(new ClassyTreeCellEditor(this,ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
         if(e.getClickCount()==2){
             ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
         }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
