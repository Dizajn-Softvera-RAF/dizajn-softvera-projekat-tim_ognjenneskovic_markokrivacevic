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
                var newClass = new Klasa("NewClass", parent, Color.BLACK, 5);
                newClass.addContent(new Atribut("Attribute 1"));
                newClass.addContent(new Metod("Method 1"));
                return newClass;
            }
            else if(currentState.getInterclassClass() == Interfejs.class)
            {
                var newClass = new Interfejs("NewInterface", parent, Color.BLACK, 5);
                newClass.addContent(new Metod("Method 1"));
                return newClass;
            }
            else
            {
                var newClass = new Enum("NewEnum", parent, Color.BLACK, 5);
                newClass.addContent(new Atribut("Attribute 1"));
                return newClass;
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
