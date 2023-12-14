package raf.dsw.classycraft.app.state;

import lombok.Getter;
import raf.dsw.classycraft.app.state.concrete.*;

public class StateManager
{
    @Getter
    private State currentState;
    private final AddClassState addClassState = new AddClassState();
    private final AddInheritanceState addInheritanceState = new AddInheritanceState();
    private final AddCompositionState addCompositionState = new AddCompositionState();
    private final SelectState selectState = new SelectState();
    private final ZoomInState zoomInState = new ZoomInState();
    private final ZoomOutState zoomOutState = new ZoomOutState();
    private final PanState panState = new PanState();
    private final MoveState moveState = new MoveState();
    public StateManager()
    {
        currentState = selectState;
    }

    public void setAddClassState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = addClassState;
    }

    public void setAddInheritanceState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = addInheritanceState;
    }
    public void setAddCompositionState(){
        currentState.stateExited();
        currentState = addCompositionState;
    }

    public void setSelectState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = selectState;
    }

    public void setZoomInState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = zoomInState;
    }

    public void setZoomOutState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = zoomOutState;
    }
    public void setPanState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = panState;
    }
    public void setMoveState()
    {
        currentState.stateExited(); // Duplicate code because of set...State methods :))
        currentState = moveState;
    }
}
