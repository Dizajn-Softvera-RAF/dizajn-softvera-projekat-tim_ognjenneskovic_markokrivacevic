package raf.dsw.classycraft.app.classyRepository.implementation.diagramElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.Interclass;

import java.awt.*;

public class Generalizacija extends Connection {
    public Generalizacija(ClassyNode parent, Color color, int strokeWidth, Interclass nodeFrom, Interclass nodeTo) {
        super(parent, color, strokeWidth, nodeFrom, nodeTo);
    }
}
