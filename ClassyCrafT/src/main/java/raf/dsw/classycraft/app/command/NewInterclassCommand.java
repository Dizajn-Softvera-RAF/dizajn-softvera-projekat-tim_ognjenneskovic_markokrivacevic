package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.factory.DiagramElementFactory;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class NewInterclassCommand<T extends Interclass> implements Command
{
    private final Interclass newNode;
    private final DiagramView diagramView;
    public NewInterclassCommand(DiagramView diagramView, Class<T> interclassClass, Diagram parent, int x, int y)
    {
        var factory = new DiagramElementFactory();
        newNode = factory.createNodeInterclass(parent, interclassClass, x, y);
        this.diagramView = diagramView;
    }
    @Override
    public void execute() {
        var tree = (ClassyTreeImplementation)MainFrame.getInstance().getClassyTree();
        tree.findNode(newNode.getParent()).add(new ClassyTreeItem(newNode));
        ((Diagram)newNode.getParent()).addChild(newNode);
        diagramView.addPainter(new InterClassPainter(newNode, newNode.getX(), newNode.getY()));
        repaint();
    }

    @Override
    public void undo() {
        // Call the appropriate delete command
        var painter = diagramView.getPainters().stream().filter(p -> p.getElement() == newNode).findFirst().get();
        var deleteCommand = new DeleteCommand(diagramView, java.util.List.of(painter));
        deleteCommand.execute();
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
