package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;

import java.util.HashMap;
import java.util.Map;

public class DiagramViewManager
{
    private static DiagramViewManager instance;
    private final Map<Diagram, DiagramView> diagramViewMap;
    private DiagramViewManager()
    {
        diagramViewMap = new HashMap<>();
    }
    public static DiagramViewManager getInstance()
    {
        if(instance == null)
            instance = new DiagramViewManager();
        return instance;
    }
    public DiagramView getDiagramView(Diagram diagram)
    {
        if(diagramViewMap.containsKey(diagram))
            return diagramViewMap.get(diagram);
        else
        {
            var diagramView = new DiagramView();
            diagramViewMap.put(diagram, diagramView);
            return diagramView;
        }
    }
}
