package raf.dsw.classycraft.app.command;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;

public class MoveCommand implements Command
{
    @Getter
    private final ArrayList<ElementPainter> selectedPainters;
    private final AffineTransform transform;
    private final AffineTransform inverseTransform;
    public MoveCommand(ArrayList<ElementPainter> selectedPainters, AffineTransform transform)
    {
        this.selectedPainters = selectedPainters;
        this.transform = transform;
        try {
            this.inverseTransform = transform.createInverse();
        } catch (NoninvertibleTransformException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Combine two move commands into one
     */
    public MoveCommand(MoveCommand cmd1, MoveCommand cmd2)
    {
        this.selectedPainters = cmd1.selectedPainters;
        this.transform = cmd1.transform;
        this.transform.concatenate(cmd2.transform);
        try {
            this.inverseTransform = this.transform.createInverse();
        } catch (NoninvertibleTransformException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void execute() {
        for(var painter: selectedPainters)
            painter.applyTransformToPoints(transform);
        repaint();
    }

    @Override
    public void undo() {
        for(var painter: selectedPainters)
            painter.applyTransformToPoints(inverseTransform);
        repaint();
    }

    @Override
    public void redo() {
        execute();
    }
    private void repaint()
    {
        var selectedDiagramView = MainFrame.getInstance().getTabbedPanel().getSelectedDiagramView();
        if (selectedDiagramView != null) {
            selectedDiagramView.paint();
        }
    }
}
