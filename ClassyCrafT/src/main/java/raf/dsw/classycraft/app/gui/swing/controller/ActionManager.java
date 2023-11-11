package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;

@Getter
public class ActionManager
{
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private AboutUsAction aboutUsAction;

    public ActionManager()
    {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
    }
}
