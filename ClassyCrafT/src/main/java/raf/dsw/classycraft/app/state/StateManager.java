package raf.dsw.classycraft.app.state;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.controller.AddClassAction;
import raf.dsw.classycraft.app.state.concrete.*;

public class StateManager
{
    @Getter
    private State currentState;
    private final AddInterclassState<Klasa> addClassState = new AddInterclassState<>(Klasa.class);
    private final AddInterclassState<Interfejs> addInterfaceState = new AddInterclassState<>(Interfejs.class);
    private final AddInterclassState<Enum> addEnumState = new AddInterclassState<>(Enum.class);
    private final AddConnectionState<Generalizacija> addGeneralizationState = new AddConnectionState<>(Generalizacija.class);
    private final AddConnectionState<Kompozicija> addCompositionState = new AddConnectionState<>(Kompozicija.class);
    private final AddConnectionState<Zavisnost> addDependencyState = new AddConnectionState<>(Zavisnost.class);
    private final AddConnectionState<Agregacija> addAggregationState = new AddConnectionState<>(Agregacija.class);

    private final SelectState selectState = new SelectState();
    private final ZoomInState zoomInState = new ZoomInState();
    private final ZoomOutState zoomOutState = new ZoomOutState();
    private final PanState panState = new PanState();
    private final MoveState moveState = new MoveState();
    private final DuplicateDiagramElementState duplicateDiagramElementState = new DuplicateDiagramElementState();
    public StateManager()
    {
        currentState = selectState;
    }

    public <T extends Interclass> void setAddInterclassState(Class<T> interclassClass)
    {
        currentState.stateExited(); 
        if (interclassClass == Klasa.class)
            currentState = addClassState;
         else if (interclassClass == Interfejs.class)
            currentState = addInterfaceState;
         else if (interclassClass == Enum.class)
            currentState = addEnumState;
    }
    public <T extends Connection> void setAddConnectionState(Class<T> connectionClass)
    {
        currentState.stateExited(); 
        if (connectionClass == Generalizacija.class)
            currentState = addGeneralizationState;
         else if (connectionClass == Kompozicija.class)
            currentState = addCompositionState;
         else if (connectionClass == Zavisnost.class)
            currentState = addDependencyState;
         else if (connectionClass == Agregacija.class)
            currentState = addAggregationState;
    }

    public void setSelectState()
    {
        currentState.stateExited(); 
        currentState = selectState;
    }

    public void setZoomInState()
    {
        currentState.stateExited(); 
        currentState = zoomInState;
    }

    public void setZoomOutState()
    {
        currentState.stateExited(); 
        currentState = zoomOutState;
    }
    public void setPanState()
    {
        currentState.stateExited(); 
        currentState = panState;
    }
    public void setMoveState()
    {
        currentState.stateExited(); 
        currentState = moveState;
    }

    public void setDuplicateDiagramElementState() {
        currentState.stateExited(); 
        currentState = duplicateDiagramElementState;
    }
}
