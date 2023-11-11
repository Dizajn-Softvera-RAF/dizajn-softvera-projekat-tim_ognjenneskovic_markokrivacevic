package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

@Getter
@Setter
public class ApplicationFramework {

    private static ApplicationFramework instance;
    protected Gui gui;
    protected ClassyRepository classyRepository;

    public void run(){
        this.gui.start();
    }

    private ApplicationFramework(){
    }


    public void initialize(Gui gui,ClassyRepository classyRepository){
          this.gui=gui;
          this.classyRepository = classyRepository;
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
