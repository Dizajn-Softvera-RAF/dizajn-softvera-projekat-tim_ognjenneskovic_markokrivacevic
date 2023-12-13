package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.ClassContent;

import java.awt.*;
import java.util.ArrayList;

public abstract class Interclass extends DiagramElement
{
    @Getter
    private final ArrayList<ClassContent> content;
    public Interclass(String name, ClassyNode parent, Color color, int strokeWidth) {
        super(name, parent, color, strokeWidth);
        this.content = new ArrayList<>();
    }
    public void addContent(ClassContent content)
    {
        this.content.add(content);
    }
    public void removeContent(ClassContent content)
    {
        this.content.remove(content);
    }
    public void clearContent()
    {
        this.content.clear();
    }
}
