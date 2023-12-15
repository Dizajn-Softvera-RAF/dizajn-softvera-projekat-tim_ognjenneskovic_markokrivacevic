package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

public class EnumElement extends ClassContent {

    public EnumElement(String name) {
        super(name);
    }

    @Override
    public ClassContent copy() {
        return new EnumElement(name);
    }
}
