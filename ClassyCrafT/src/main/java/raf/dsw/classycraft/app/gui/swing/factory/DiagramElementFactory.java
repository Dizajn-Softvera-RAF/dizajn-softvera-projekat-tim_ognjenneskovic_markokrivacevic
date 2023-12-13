package raf.dsw.classycraft.app.gui.swing.factory;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.concrete.AddClassState;
import raf.dsw.classycraft.app.state.concrete.AddInheritanceState;

import java.awt.*;

public class DiagramElementFactory extends NodeFactory
{
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        var currentState = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().getStateManager().getCurrentState();
        if(currentState instanceof AddClassState) {
            var newClass = new Klasa("NewClass", parent, Color.BLACK, 5);
            newClass.addContent(new Atribut("Attribute 1"));
            newClass.addContent(new Metod("Method 1"));

            return newClass;
        }
        else if(currentState instanceof AddInheritanceState) // TODO: Implement this properly
            return new Generalizacija(parent, Color.BLACK, 5, null, null);
        else
            return null;
    }
}
