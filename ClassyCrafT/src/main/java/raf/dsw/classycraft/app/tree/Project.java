package raf.dsw.classycraft.app.tree;

public class Project extends ClassyNodeComposite
{
    private String author, path;
    public Project(String authorName, String projectPath)
    {
        author = authorName;
        path = projectPath;
    }

}
