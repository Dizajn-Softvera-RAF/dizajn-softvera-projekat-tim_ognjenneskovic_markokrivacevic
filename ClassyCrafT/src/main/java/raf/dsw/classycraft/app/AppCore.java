package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.classyRepository.ClassyNodeImplementation;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.SwingGui;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        ClassyRepository classyRepository = new ClassyNodeImplementation();
        appCore.initialize(gui,classyRepository);
        appCore.run();
    }
}