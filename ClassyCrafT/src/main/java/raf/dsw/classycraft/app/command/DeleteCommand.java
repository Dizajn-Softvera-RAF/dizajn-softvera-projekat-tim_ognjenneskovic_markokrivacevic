package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteCommand implements Command{

    private DiagramView diagramView;
    private List<ElementPainter> toDelete;

    public DeleteCommand(DiagramView diagramView, List<ElementPainter> selected) {
        this.diagramView = diagramView;
        toDelete = getToDelete(selected);
    }

    private List<ElementPainter> getToDelete(List<ElementPainter> selected)
    {
        // For all interclass elements find all connections that are connected to them
        // and add them to the list of elements to delete

        // Add all interclass elements to the list of elements to delete
        var toDelete = selected.stream().filter(p-> p.getElement() instanceof Interclass)
                .collect(Collectors.toList());
        var connectionPainters = diagramView.getPainters().stream()
                .filter(p -> p instanceof ConnectionPainter)
                .collect(Collectors.toList());
        for(var connectionPainter: connectionPainters)
        {
            var connection = (Connection)connectionPainter.getElement();
            if(selected.stream().anyMatch(p -> p.getElement() == connection.getNodeFrom() ||
                            p.getElement() == connection.getNodeTo()))
                toDelete.add(connectionPainter);
        }
        return toDelete;
    }

    private void deleteAssocToTopic(DiagramElement element){
        Interclass interclass = (Interclass)element;
        List<ElementPainter> paintersforCurr = diagramView.getPainters();
        List<ElementPainter> connectionToDelete = new ArrayList<>();

        for(ElementPainter p : paintersforCurr){
            if(p instanceof ConnectionPainter){
                Connection con = (Connection) p.getElement();
                if(con.getNodeFrom().equals(interclass) || con.getNodeTo().equals(interclass)){
                    toDelete.add(p);
                    connectionToDelete.add(p);
                }
            }
        }
        for(ElementPainter p : connectionToDelete){
            diagramView.deletePainterForCurrent(p);
        }
    }

    @Override
    public void execute() {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            // Remove selected elements
            for(var painter: toDelete)
            {
                // Remove element from diagram
                selectedDiagramView.getPainters().remove(painter);
                // Remove element from tree
                var diagramElement = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findNode(painter.getElement());
                if (diagramElement != null)
                    MainFrame.getInstance().getClassyTree().removeChild(diagramElement);
            }
            repaint();
        }
    }

    @Override
    public void undo()
    {
        for(var painter: toDelete)
        {
            var newNode = painter.getElement();
            var tree = (ClassyTreeImplementation)MainFrame.getInstance().getClassyTree();
            tree.findNode(newNode.getParent()).add(new ClassyTreeItem(newNode));
            ((Diagram)newNode.getParent()).addChild(newNode);
            if(newNode instanceof Interclass)
            {
                var interclass = (Interclass)newNode;
                diagramView.addPainter(new InterClassPainter(interclass, interclass.getX(), interclass.getY()));
            }
            else if(newNode instanceof Connection)
            {
                var connection = (Connection)newNode;
                diagramView.addPainter(new ConnectionPainter<>(connection, connection.getX(), connection.getY(),
                        connection.getEndX(), connection.getEndY()));
            }
        }
        repaint();
    }

    @Override
    public void redo() {
        execute();
    }
    private void repaint()
    {
        diagramView.repaint();
    }
}
