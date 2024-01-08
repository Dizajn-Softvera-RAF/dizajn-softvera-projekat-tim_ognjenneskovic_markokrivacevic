package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;

import java.io.File;

public interface Serializer {

    Project loadProject (File file);
    void saveProject (Project node);
    void loadTemplate(File file);
    void saveTemplate(Diagram diagram, String templateName);

}
