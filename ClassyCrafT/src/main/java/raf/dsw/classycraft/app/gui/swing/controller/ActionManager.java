package raf.dsw.classycraft.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

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
    private AddInterfaceAction addInterfaceAction;
    private AddEnumAction addEnumAction;
    private AddDependencyAction addDependencyAction;
    private AddGeneralisationAction addGeneralisationAction;
    private AddCompositionAction addCompositionAction;
    private AddAggregationAction addAggregationAction;
    private SelectAction selectAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private ZoomToFitAction zoomToFitAction;
    private PanAction panAction;
    private MoveAction moveAction;
    private RemoveDiagramElementAction removeDiagramElementAction;
    private DuplicateDiagramElementAction duplicateDiagramElementAction;
    private PictureAction pictureAction;
    private SaveProjectAction saveProjectAction;
    private OpenProjectAction openProjectAction;
    private SaveTemplateAction saveTemplateAction;
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
        addEnumAction = new AddEnumAction();
        addInterfaceAction = new AddInterfaceAction();

        addDependencyAction = new AddDependencyAction();
        addGeneralisationAction = new AddGeneralisationAction();
        addAggregationAction = new AddAggregationAction();
        addCompositionAction = new AddCompositionAction();

        selectAction = new SelectAction();

        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        zoomToFitAction = new ZoomToFitAction();

        panAction = new PanAction();
        moveAction = new MoveAction();

        removeDiagramElementAction = new RemoveDiagramElementAction();
        duplicateDiagramElementAction = new DuplicateDiagramElementAction();

        pictureAction = new PictureAction();
        saveProjectAction = new SaveProjectAction();
        openProjectAction = new OpenProjectAction();
        saveTemplateAction = new SaveTemplateAction();
    }

}
