package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Atribut;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Metod;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class AddClassState implements State
{

    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {
        var diagram = diagramView.getSelectedDiagram().getClassyNode();

        var newClass = new Klasa("NewClass", diagram, Color.BLACK, 5);
        newClass.addContent(new Atribut("Attribute 1"));
        newClass.addContent(new Atribut("Attribute 2"));
        newClass.addContent(new Metod("Method 1"));
        newClass.addContent(new Metod("Method 2"));
        newClass.addContent(new Metod("Method 3"));
        diagramView.addElement(newClass, x, y);
    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {
        if (diagramView.getTempRectPainter() == null)
        {
            diagramView.setTempRectPainter(new TempRectanglePainter(null, x, y));
        }
        else
        {
            diagramView.getTempRectPainter().setPos(x, y);
        }
        diagramView.paint();

    }
}
