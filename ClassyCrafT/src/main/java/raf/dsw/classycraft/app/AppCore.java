package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.classyRepository.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.serializer.GsonSerializer;
import raf.dsw.classycraft.app.serializer.Serializer;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        raf.dsw.classycraft.app.core.ClassyRepository classyRepository = new ClassyRepositoryImplementation();
        Serializer serializer = new GsonSerializer();
        appCore.initialize(gui,classyRepository,serializer);
        appCore.run();
    }
}