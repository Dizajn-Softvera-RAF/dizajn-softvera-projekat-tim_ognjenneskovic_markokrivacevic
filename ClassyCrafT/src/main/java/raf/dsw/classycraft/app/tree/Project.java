package raf.dsw.classycraft.app.tree;

public class Project extends ClassyNodeComposite
{
    private String author, path;
    public Project(String authorName, String projectPath, String projectName, ClassyNodeComposite parent)
    {
        super(projectName, parent);
        author = authorName;
        path = projectPath;
        name = projectName;
    }
    public String getAuthor() {
        return author;
    }
}
