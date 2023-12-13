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
    private AddAssociationAction addAssociationAction;
    private AddGeneralisationAction addGeneralisationAction;
    private AddCompositionAction addCompositionAction;
    private AddAggregationAction addAggregationAction;
    private SelectAction selectAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private ZoomToFitAction zoomToFitAction;
    private PanAction panAction;
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
        addAssociationAction = new AddAssociationAction();
        addGeneralisationAction = new AddGeneralisationAction();
        addAggregationAction = new AddAggregationAction();
        addCompositionAction = new AddCompositionAction();
        selectAction = new SelectAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        zoomToFitAction = new ZoomToFitAction();
        panAction = new PanAction();
        moveAction = new MoveAction();
    }
}
