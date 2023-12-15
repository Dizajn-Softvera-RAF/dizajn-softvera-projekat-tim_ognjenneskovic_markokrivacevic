package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

public class Atribut extends ClassContent
{
    public Atribut(String name) {
        super(name);
    }

    @Override
    public ClassContent copy() {
        return new Atribut(name);
    }
}
