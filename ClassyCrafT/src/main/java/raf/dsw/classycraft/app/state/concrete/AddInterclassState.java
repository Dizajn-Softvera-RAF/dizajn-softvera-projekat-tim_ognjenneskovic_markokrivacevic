package raf.dsw.classycraft.app.state.concrete;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.TempElement;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.InterClassPainter;
import raf.dsw.classycraft.app.classyRepository.implementation.painters.TempRectanglePainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;

public class AddInterclassState<T extends Interclass> implements State
{
    @Getter
    private final Class<T> interclassClass;
    public AddInterclassState(Class<T> interclassClass)
    {
        this.interclassClass = interclassClass;
    }
    @Override
    public void mousePressed(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseClicked(int x, int y, DiagramView diagramView) {
        var diagram = (Diagram) diagramView.getSelectedDiagram().getClassyNode();

        var tempClass = new Klasa("NewClass", diagram, Color.BLACK, 5);
        var painter = new InterClassPainter(tempClass, x, y);
        var bb = painter.getBoundingBox();
        if (diagramView.canAddInterClass(tempClass, bb))
        {
            MainFrame.getInstance().getClassyTree().addChild(diagramView.getSelectedDiagram());
            var newClass = (DiagramElement) diagram.getChildren().get(diagram.getChildren().size() - 1);
            painter = new InterClassPainter(newClass, x, y);
            diagramView.addPainter(painter);
        }
    }

    @Override
    public void mouseReleased(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void mouseMoved(int x, int y, DiagramView diagramView) {
        // Set painter for temp rectangle
        diagramView.setTempRectPainter(new TempRectanglePainter(new TempElement(), x, y));
        // Force repaint
        diagramView.paint();
    }

    @Override
    public void mouseDragged(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void stateExited() {
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().setTempRectPainter(null);
        MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView().paint();
    }
}
