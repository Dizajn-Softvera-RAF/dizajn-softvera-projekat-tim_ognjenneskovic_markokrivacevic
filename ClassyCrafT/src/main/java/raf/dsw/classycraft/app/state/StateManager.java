package raf.dsw.classycraft.app.state;

import lombok.Getter;
import raf.dsw.classycraft.app.state.concrete.AddClassState;
import raf.dsw.classycraft.app.state.concrete.AddInheritanceState;
import raf.dsw.classycraft.app.state.concrete.SelectState;

public class StateManager
{
    @Getter
    private State currentState;
    private final AddClassState addClassState = new AddClassState();
    private final AddInheritanceState addInheritanceState = new AddInheritanceState();
    private final SelectState selectState = new SelectState();

    public StateManager()
    {
        this.currentState = selectState;
    }

    public void setAddClassState()
    {
        currentState = addClassState;
    }

    public void setAddInheritanceState()
    {
        currentState = addInheritanceState;
    }

    public void setSelectState()
    {
        currentState = selectState;
    }
}
