package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

import lombok.Getter;

public abstract class ClassContent
{
    @Getter
    protected String name;
    public ClassContent(String name)
    {
        this.name = name;
    }
    public abstract ClassContent copy();
    @Override
    public String toString() {
        return name;
    }
}
