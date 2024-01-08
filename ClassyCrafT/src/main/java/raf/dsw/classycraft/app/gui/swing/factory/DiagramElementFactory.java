package raf.dsw.classycraft.app.gui.swing.factory;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.concrete.AddInterclassState;
import raf.dsw.classycraft.app.state.concrete.AddConnectionState;
import raf.dsw.classycraft.app.state.concrete.DuplicateDiagramElementState;

import java.awt.*;

public class DiagramElementFactory extends NodeFactory
{
    public <T extends Interclass> Interclass createNodeInterclass(Diagram parent,
                                                                  Class<T> interclassClass, int x, int y)
    {
        if(interclassClass == Klasa.class)
        {
            var classColor = new Color(42,157,143);
            var newClass = new Klasa("NewClass", parent, classColor, 5, x, y);
            newClass.addContent(new Atribut("private int x"));
            newClass.addContent(new Metod("public void method1()"));
            return newClass;
        }
        else if(interclassClass == Interfejs.class)
        {
            var interfaceColor = new Color(233, 196, 106);
            var newInterface = new Interfejs("NewInterface", parent, interfaceColor, 5, x, y);
            newInterface.addContent(new Metod("public void method1()"));
            return newInterface;
        }
        else
        {
            var enumColor = new Color(233, 111, 81);
            var newEnum = new Enum("NewEnum", parent, enumColor, 5, x, y);
            newEnum.addContent(new EnumElement("VALUE1"));
            return newEnum;
        }
    }
    public <T extends Connection> Connection createNodeConnection(Diagram parent, Class<T> connectionClass,
                                                                  Interclass nodeFrom, Interclass nodeTo,
                                                                  int x, int y, int endX, int endY)
    {
        if(connectionClass == Generalizacija.class)
            return new Generalizacija(parent, Color.BLACK, 5, nodeFrom, nodeTo, x, y, endX, endY);

        else if(connectionClass == Agregacija.class)
            return new Agregacija(parent, Color.BLACK, 5, nodeFrom, nodeTo, x, y, endX, endY);

        else if(connectionClass == Kompozicija.class)
            return new Kompozicija(parent, Color.BLACK, 5, nodeFrom, nodeTo, x, y, endX, endY);

        else if(connectionClass == Zavisnost.class)
            return new Zavisnost(parent, Color.BLACK, 5, nodeFrom, nodeTo, x, y, endX, endY);
        else
            return null;
    }
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        var currState = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().getStateManager().getCurrentState();
        if(currState instanceof AddInterclassState) {
            var currentState = (AddInterclassState<?>) currState;
            int x = currentState.getX();
            int y = currentState.getY();
            return createNodeInterclass((Diagram) parent, currentState.getInterclassClass(), x, y);
        }
        else if(currState instanceof AddConnectionState)
        {
            var currentState = (AddConnectionState<?>) currState;
            var nodeFrom = currentState.getNodeFrom();
            var nodeTo = currentState.getNodeTo();
            int x = currentState.getStartPoint().x;
            int y = currentState.getStartPoint().y;
            int endX = currentState.getEndPoint().x;
            int endY = currentState.getEndPoint().y;
            return createNodeConnection((Diagram) parent, currentState.getConnectionClass(),
                    nodeFrom, nodeTo, x, y, endX, endY);
        }
        else if(currState instanceof DuplicateDiagramElementState)
        {
            var diagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
            var selected = diagramView.getSelectedPainters();
            var element = selected.get(0).getElement();
            return element.copy();
        }
        else
            return null;
    }
}
