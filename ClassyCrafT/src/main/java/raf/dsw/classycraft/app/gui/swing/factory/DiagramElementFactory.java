package raf.dsw.classycraft.app.gui.swing.factory;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.concrete.AddInterclassState;
import raf.dsw.classycraft.app.state.concrete.AddConnectionState;

import java.awt.*;

public class DiagramElementFactory extends NodeFactory
{
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        var currState = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().getStateManager().getCurrentState();
        if(currState instanceof AddInterclassState) {
            var currentState = (AddInterclassState<?>) currState;
            if(currentState.getInterclassClass() == Klasa.class)
            {
                var classColor = new Color(42,157,143);
                var newClass = new Klasa("NewClass", parent, classColor, 5);
                newClass.addContent(new Atribut("private int x"));
                newClass.addContent(new Metod("public void method1()"));
                return newClass;
            }
            else if(currentState.getInterclassClass() == Interfejs.class)
            {
                var interfaceColor = new Color(233, 196, 106);
                var newInterface = new Interfejs("NewInterface", parent, interfaceColor, 5);
                newInterface.addContent(new Metod("public void method1()"));
                return newInterface;
            }
            else
            {
                var enumColor = new Color(233, 111, 81);
                var newEnum = new Enum("NewEnum", parent, enumColor, 5);
                newEnum.addContent(new EnumElement("VALUE1"));
                return newEnum;
            }

        }
        else if(currState instanceof AddConnectionState)
        {
            var currentState = (AddConnectionState<?>) currState;
            var nodeFrom = currentState.getNodeFrom();
            var nodeTo = currentState.getNodeTo();
            if(currentState.getConnectionClass() == Generalizacija.class)
                return new Generalizacija(parent, Color.BLACK, 5, nodeFrom, nodeTo);

            else if(currentState.getConnectionClass() == Agregacija.class)
                return new Agregacija(parent, Color.BLACK, 5, nodeFrom, nodeTo);

            else if(currentState.getConnectionClass() == Kompozicija.class)
                return new Kompozicija(parent, Color.BLACK, 5, nodeFrom, nodeTo);

            else if(currentState.getConnectionClass() == Zavisnost.class)
                return new Zavisnost(parent, Color.BLACK, 5, nodeFrom, nodeTo);

            else
                return null;
        }
        else
            return null;
    }
}
