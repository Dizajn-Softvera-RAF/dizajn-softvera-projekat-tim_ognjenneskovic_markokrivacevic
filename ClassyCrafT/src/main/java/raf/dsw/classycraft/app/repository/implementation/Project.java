package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite
{
    private String author, path;
    public Project(String authorName, String projectPath, String projectName, ClassyNodeComposite parent)
    {
        super(projectName, parent);
        author = authorName;
        path = projectPath;
        this.setName(projectName);
    }
    public String getAuthor() {
        return author;
    }
}
