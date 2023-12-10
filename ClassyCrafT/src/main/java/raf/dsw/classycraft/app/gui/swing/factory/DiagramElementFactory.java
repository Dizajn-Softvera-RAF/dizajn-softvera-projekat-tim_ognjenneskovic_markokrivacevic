package raf.dsw.classycraft.app.gui.swing.factory;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Generalizacija;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Zavisnost;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.concrete.AddClassState;
import raf.dsw.classycraft.app.state.concrete.AddInheritanceState;

import java.awt.*;

public class DiagramElementFactory extends NodeFactory
{
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {
        var currentState = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().getStateManager().getCurrentState();
        if(currentState instanceof AddClassState)
            return new Klasa("New Class", parent, Color.BLACK, 5);
        else if(currentState instanceof AddInheritanceState) // TODO: Implement this properly
            return new Generalizacija(parent, Color.BLACK, 5, null, null);
        else
            return null;
    }
}
