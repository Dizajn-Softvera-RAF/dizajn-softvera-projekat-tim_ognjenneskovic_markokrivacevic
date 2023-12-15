package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

public class Metod extends ClassContent {

    public Metod(String name) {
        super(name);
    }

    @Override
    public ClassContent copy() {
        return new Metod(name);
    }
}
