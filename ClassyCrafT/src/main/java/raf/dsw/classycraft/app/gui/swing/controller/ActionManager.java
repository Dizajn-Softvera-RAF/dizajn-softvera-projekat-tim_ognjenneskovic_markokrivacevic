package raf.dsw.classycraft.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private DeleteChildAction deleteChildAction;

    private AuthorNameAction authorNameAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        deleteChildAction = new DeleteChildAction();
        authorNameAction = new AuthorNameAction();
    }

}
