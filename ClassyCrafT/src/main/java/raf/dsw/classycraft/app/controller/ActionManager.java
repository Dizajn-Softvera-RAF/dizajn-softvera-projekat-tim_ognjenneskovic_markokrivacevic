package raf.dsw.classycraft.app.controller;

import lombok.Getter;

@Getter
public class ActionManager
{
    private ExitAction exitAction;
    private NewDiagramAction newDiagramAction;
    private NewPacketAction newPackageAction;
    private NewProjectAction newProjectAction;
    private AboutUsAction aboutUsAction;

    public ActionManager()
    {
        exitAction = new ExitAction();
        newDiagramAction = new NewDiagramAction();
        newPackageAction = new NewPacketAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
    }
}
