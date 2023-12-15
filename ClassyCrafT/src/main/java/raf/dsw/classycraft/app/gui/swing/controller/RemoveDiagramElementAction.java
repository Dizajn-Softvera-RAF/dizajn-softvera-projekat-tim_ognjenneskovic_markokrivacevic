package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class RemoveDiagramElementAction extends AbstractClassyAction{
    public RemoveDiagramElementAction(){
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete");
        putValue(SMALL_ICON,loadIcon("/images/delete2.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            var selected = selectedDiagramView.getSelectedPainters();
            // Remove selected elements
            for(var painter: selected)
            {
                // Remove element from diagram
                selectedDiagramView.getPainters().remove(painter);
                // Remove element from tree
                var treeNode = selectedDiagramView.getSelectedDiagram();
                // Find element in children of current diagram
                for(var child: Collections.list(treeNode.children()))
                {
                    var diagramElement = (ClassyTreeItem)child;
                    if (diagramElement.getClassyNode() == painter.getElement()) {
                        MainFrame.getInstance().getClassyTree().removeChild(diagramElement);
                        break;
                    }
                }

            }
            // Repaint
            selectedDiagramView.repaint();
        }
    }
}
