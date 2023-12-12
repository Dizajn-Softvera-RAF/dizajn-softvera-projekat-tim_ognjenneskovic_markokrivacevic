package raf.dsw.classycraft.app.observer;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public interface MouseFunction {
    void handleEvent(int x, int y, DiagramView diagramView);
}
