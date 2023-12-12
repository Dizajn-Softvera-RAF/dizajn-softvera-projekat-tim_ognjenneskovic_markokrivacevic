package raf.dsw.classycraft.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewNodeAction newNodeAction;
    private DeleteChildAction deleteChildAction;

    private AuthorNameAction authorNameAction;
    private NewPackageAction newPackageAction;
    private AddClassAction addClassAction;
    private AddInheritanceAction addInheritanceAction;
    private SelectAction selectAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private MoveAction moveAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newNodeAction = new NewNodeAction();
        deleteChildAction = new DeleteChildAction();
        authorNameAction = new AuthorNameAction();
        newPackageAction = new NewPackageAction();

        addClassAction = new AddClassAction();
        addInheritanceAction = new AddInheritanceAction();
        selectAction = new SelectAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        moveAction = new MoveAction();
    }
}
