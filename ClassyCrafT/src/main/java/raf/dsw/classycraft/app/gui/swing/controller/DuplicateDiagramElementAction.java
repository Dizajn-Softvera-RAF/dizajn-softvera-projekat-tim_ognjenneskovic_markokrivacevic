package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class DuplicateDiagramElementAction extends AbstractClassyAction{
    public DuplicateDiagramElementAction(){
        putValue(NAME,"Duplicate");
        putValue(SHORT_DESCRIPTION,"Duplicate");
        putValue(SMALL_ICON,loadIcon("/images/copy.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.startDuplicateDiagramElementState();
            var selectedPainters = selectedDiagramView.getSelectedPainters();
            if(selectedPainters.size() != 1)
                return;

            // Create duplicate in tree
            ClassyTreeItem selected = selectedDiagramView.getSelectedDiagram();
            MainFrame.getInstance().getClassyTree().addChild(selected);

            // Create duplicate painter
            var selectedDiagram = selectedDiagramView.getSelectedDiagram();
            var copyDiagramElement = (ClassyTreeItem) selectedDiagram.getLastChild();
            var painterDuplicate = selectedPainters.get(0).copy();
            painterDuplicate.setElement((DiagramElement) copyDiagramElement.getClassyNode());
            // Offset the painter
            painterDuplicate.applyTransformToPoints(AffineTransform.getTranslateInstance(20,20));

            // Mark old element as unselected
            selectedPainters.get(0).getElement().markUnselected();
            // Mark new element as selected
            painterDuplicate.getElement().markSelected();

            // Add duplicate painter to diagram view and repaint
            selectedDiagramView.addPainter(painterDuplicate);
            selectedDiagramView.repaint();
            // Transition to move state
            selectedDiagramView.startMoveState();
        }
    }
}
