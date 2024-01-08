package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.factory.DiagramElementFactory;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.ProjectView;

import java.awt.*;
import java.util.ArrayList;

public class NewConnectionCommand implements Command
{
    private final DiagramView diagramView;
    private final Connection connection;
    public NewConnectionCommand(DiagramView diagramView, Class<? extends Connection> connectionClass,
                                Diagram parent, Interclass nodeFrom, Interclass nodeTo,
                                int x, int y, int endX, int endY){
        this.diagramView = diagramView;
        var factory = new DiagramElementFactory();
        connection = factory.createNodeConnection(parent, connectionClass,
                nodeFrom, nodeTo, x, y, endX, endY);
    }

    @Override
    public void execute() {
        var tree = (ClassyTreeImplementation)MainFrame.getInstance().getClassyTree();
        tree.findNode(connection.getParent()).add(new ClassyTreeItem(connection));
        ((Diagram)connection.getParent()).addChild(connection);
        diagramView.addPainter(new ConnectionPainter<>(connection, connection.getX(), connection.getY(),
                connection.getEndX(), connection.getEndY()));
        repaint();
    }

    @Override
    public void undo() {
        // Call the appropriate delete command
        var painter = diagramView.getPainters().stream().filter(p -> p.getElement() == connection).findFirst().get();
        var deleteCommand = new DeleteCommand(diagramView, new ArrayList<>(java.util.List.of(painter)));
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
