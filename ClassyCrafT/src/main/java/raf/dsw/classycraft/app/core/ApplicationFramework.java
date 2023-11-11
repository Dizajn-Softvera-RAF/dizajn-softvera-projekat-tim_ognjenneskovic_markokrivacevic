package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logger.ConsoleLogger;
import raf.dsw.classycraft.app.logger.FileLogger;
import raf.dsw.classycraft.app.messageGenerator.MessageGeneratorImplementation;

@Getter
@Setter
public class ApplicationFramework {

    private static ApplicationFramework instance;
    protected Gui gui;
    protected ClassyRepository classyRepository;
    private MessageGeneratorImplementation messageGenerator;


    public void run(){
        this.gui.start();
    }

    private ApplicationFramework(){
    }


    public void initialize(Gui gui,ClassyRepository classyRepository){
          this.gui=gui;
          this.classyRepository = classyRepository;
          this.messageGenerator = new MessageGeneratorImplementation();

          // Add loggers to msg generator
          var consoleLogger = new ConsoleLogger();
          var fileLogger = new FileLogger();
          messageGenerator.addSubscriber(consoleLogger);
          messageGenerator.addSubscriber(fileLogger);
          messageGenerator.addSubscriber(MainFrame.getInstance());
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
